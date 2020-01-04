package algorithm_practice.Coding_Interview_Guide_2ndEdition.Chapter_01_栈和队列;

import junit.framework.TestCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
【题目】
实现一种狗猫队列的结构，要求如下：
用户可以调用add 方法将cat 类或dog 类的实例放入队列中；
用户可以调用pollAll 方法，将队列中所有的实例按照进队列的先后顺序依次弹出；
用户可以调用pollDog 方法，将队列中dog 类的实例按照进队列的先后顺序依次弹出；
用户可以调用pollCat 方法，将队列中cat 类的实例按照进队列的先后顺序依次弹出；
用户可以调用isEmpty 方法，检查队列中是否还有dog 或cat 的实例；
用户可以调用isDogEmpty 方法，检查队列中是否有dog 类的实例；
用户可以调用isCatEmpty 方法，检查队列中是否有cat 类的实例。

【难度】
士 ★☆☆☆
 */
public class P04_猫狗队列 extends TestCase {

    @Getter
    @AllArgsConstructor
    public class Pet {
        private String type;
    }

    public class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }
    public class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }


    @Test
    public void testCase() {

    }

    @Getter
    @AllArgsConstructor
    class PetEnterQueue {
        private Pet pet;
        private long count; //此Pet进入队列的时间戳

        public String getEnterPetType() {
            return pet.getType();
        }
    }

    class CatDowQueue {
        private Queue<PetEnterQueue> dogQ;
        private Queue<PetEnterQueue> catQ;
        private long count;  //进入大队列的时间戳

        public CatDowQueue() {
            dogQ = new LinkedList<PetEnterQueue>();
            catQ = new LinkedList<PetEnterQueue>();
            count = 0;
        }

        public void add(Pet pet) {
            if ("dog".equals(pet.getType())) {
                this.dogQ.add(new PetEnterQueue(pet, count++));
            } else if ("cat".equals(pet.getType())) {
                this.catQ.add(new PetEnterQueue(pet, count++));
            } else {
                throw new RuntimeException("err, not dog, not cat");
            }
        }


    }
}
