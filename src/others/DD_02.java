package others;

import java.util.Scanner;

/**
 * 小青蛙逃离迷宫
 * Created by nibnait on 2016/9/18.
 */
public class DD_02 {
    private static int n, m, p;
    private static int[][] d = new int[20][20];
    private static boolean flag;
    private static int[][] ans = new int[100][2];
    private static int[][] tans = new int[100][2];
    private static int[] px = new int[]{0, 0, 1, -1};
    private static int[] py = new int[]{1, -1, 0, 0};
    private static int[] pp = new int[]{1, 1, 0, 3};
    private static int ians = 1000;
    private static int nowindex;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] str = s.split(" ");
        n = Integer.valueOf(str[0]);
        m = Integer.valueOf(str[1]);
        p = Integer.valueOf(str[2]);
        for (int i = 0; i < n; i++) {
            s = sc.nextLine();
            str = s.split(" ");
            for (int j = 0; j < m; j++) {
                d[i][j] = Integer.valueOf(str[j]);
            }
        }

        d[1][1] = 0;
        flag = false;
        dfs(1,1,0,0);
        if (flag){
            print();
        }else {
            System.out.println("Can not escape!");
        }
    }
    private static void print(){
        for (int i = 0; i < nowindex; i++) {
            System.out.println("["+(tans[i][0]-1)+","+ (tans[i][1]-1)+"],");
        }
        System.out.println("["+(tans[nowindex][0]-1)+","+ (tans[nowindex][1]-1)+"]");
    }

    private static void dfs(int x, int y, int tp, int index) {
        ans[index][0] = x;
        ans[index][1] = y;
        if(p < tp || tp > ians)
            return;

        if(x == 1 && y == m)
        {
            if(ians > tp)
            {
                ians = tp;
                nowindex = index;
                for(int i=0; i<=index; i++)
                {
                    tans[i][0] = ans[i][0];
                    tans[i][1] = ans[i][1];
                }
            }
            flag = true;
            return;
        }
        for(int i=0; i<4; i++)
        {
            int tx = px[i] + x;
            int ty = py[i] + y;
            if(d[tx][ty] == 1)
            {
                d[tx][ty] = 0;
                dfs(tx, ty, tp+pp[i], index+1);
                d[tx][ty] = 1;
            }
        }
    }

}
/*
#include <iostream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <stack>

using namespace std;

#define N 20

int n, m, p;
int d[N][N];
bool flag;
int ans[100][2], tans[100][2];
int px[4] = {0, 0, 1, -1};
int py[4] = {1, -1, 0, 0};
int pp[4] = {1, 1, 0, 3};
int ians = 1000;
int nowindex;

void print()
{
    for(int i=0; i<nowindex; i++)
        printf("[%d,%d],", tans[i][0]-1, tans[i][1]-1);
    printf("[%d,%d]\n", tans[nowindex][0]-1, tans[nowindex][1]-1);
}

void dfs(int x, int y, int tp, int index)
{
    ans[index][0] = x;
    ans[index][1] = y;
    //cout<<x<<" "<<y<<" "<<tp<<" "<<ians<<endl;
    if(p < tp || tp > ians)
        return;

    if(x == 1 && y == m)
    {
        if(ians > tp)
        {
            ians = tp;
            nowindex = index;
            for(int i=0; i<=index; i++)
            {
                tans[i][0] = ans[i][0];
                tans[i][1] = ans[i][1];
            }
        }
        flag = true;
        return;
    }
    for(int i=0; i<4; i++)
    {
        int tx = px[i] + x;
        int ty = py[i] + y;
        if(d[tx][ty] == 1)
        {
            //cout<<"-=-="<<tx<<ty;
            d[tx][ty] = 0;
            dfs(tx, ty, tp+pp[i], index+1);
            d[tx][ty] = 1;
        }
    }
}

int main()
{
    scanf("%d%d%d", &n, &m, &p);
    for(int i=1; i<=n; i++)
        for(int j=1; j<=m; j++)
            scanf("%d", &d[i][j]);
    d[1][1] = 0;
    flag = false;
    dfs(1, 1, 0, 0);
    if(flag)
        print();
    else
        printf("Can not escape!\n");

    return 0;
}
 */
