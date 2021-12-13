package BomberGame.Entity.Enemy;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Snake extends Balloon {
    public Snake(int x, int y) {
        super(x, y);
    }

    //đuổi theo nhân vật
    public boolean canMove(int[][] arr) {
        /*
        int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
        int h = arr.length;
        int l = arr[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[h][l];
        while (!queue.isEmpty()) {
            for()
        }

         */
        return true;

    }

    //xet xem co the den vi tri nhan vat duoc k? :
    //Input: mang 2 chieu, vi tri bat dau, vi tri ket thuc
    //dung Queue luu tru cac vi tri xung quanh xem co the di dc k? Neu duoc thi them vao Queue
    //Diem nao duyet roi thi danh dau.
    //Xet tiep den cac diem tiep. Den khi chua diem ket thuc hoac k con diem nao de xet thi dung
    //Tra ve true false


    public void autoSmartMove(int[][] arr) {
        if(canMove(arr)) {

        }
    }
}
