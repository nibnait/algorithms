local storedPermits = 'storedPermits'
local maxPermits = 'maxPermits'
local stableIntervalMicros = 'stableIntervalMicros'
local nextFreeTicketMicros = 'nextFreeTicketMicros'

local function reserveAndGetWaitLength(key, permits, nowMicros)
    local limiterInfo = redis.call('HMGET', key, storedPermits, maxPermits, stableIntervalMicros, nextFreeTicketMicros)
    local stored = tonumber(limiterInfo[1])
    local max = tonumber(limiterInfo[2])
    local interval = tonumber(limiterInfo[3])
    local nextMicros = tonumber(limiterInfo[4])
    if (nowMicros >= nextMicros)
    then
        local newPermits = (nowMicros - nextMicros) / interval
        local newStored = math.min(max, stored + newPermits)
        redis.call('HMSET', key, storedPermits, newStored, nextFreeTicketMicros, nowMicros)
    end

    nextMicros = tonumber(redis.call('HGET', key, nextFreeTicketMicros))
    stored = tonumber(redis.call('HGET', key, storedPermits))
    local storedToSpend = math.min(stored, permits)
    local freshPermits = permits - storedToSpend
    local waitMicros = freshPermits * interval
    local newNextMicros = nextMicros + waitMicros
    stored = stored - storedToSpend
    redis.call('HMSET', key, storedPermits, stored, nextFreeTicketMicros, newNextMicros)
    return newNextMicros
end

local function acquire(key, permits, nowMicros)
    local wait = reserveAndGetWaitLength(key, permits, nowMicros)
    return math.max(wait - nowMicros, 0)
end

local function tryAcquire(key, permits, nowMicros, timeoutMicros)
    local next = tonumber(redis.call('HGET', key, nextFreeTicketMicros))
    if (nowMicros + timeoutMicros <= next)
    then
        -- tryAcquire false
        return -1
    else
        local wait = reserveAndGetWaitLength(key, permits, nowMicros)
        return math.max(wait - nowMicros, 0)
    end
end

local key = KEYS[1]
local method = ARGV[1]
if method == 'acquire' then
    return acquire(key, tonumber(ARGV[2]), tonumber(ARGV[3]))
elseif method == 'tryAcquire' then
    return tryAcquire(key, tonumber(ARGV[2]), tonumber(ARGV[3]), tonumber(ARGV[4]))
end
