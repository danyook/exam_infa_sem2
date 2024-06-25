package demo_exam.v1_305.n1;


public class MatrixCode {
    private int n;
    private MyLinkedList<MatrixElem> list;

    public MatrixCode(int[][] matrix) {
        this.n = matrix.length;
        doLinkedMatrix(matrix);
    }

    public int[][] decode() {
        int[][] arr = new int[n][n];
        for (int i = 0; i < list.size; i++) {
            MatrixElem nodeMatrix = list.get(i);
            arr[nodeMatrix.getI()][nodeMatrix.getJ()] = nodeMatrix.getValue();
        }
        return arr;
    }

    private void doLinkedMatrix(int[][] arr) {
        this.list = new MyLinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != 0) {
                    list.add(new MatrixElem(i, j, arr[i][j]));
                }
            }
        }
    }

    public void insert(int i, int j, int value) {
        MatrixElem nodeMatrix;
        for (int k = 0; k < list.size; k++) {
            nodeMatrix = list.get(k);
            if (nodeMatrix.getI() == i && nodeMatrix.getJ() == j) {
                list.get(k).setValue(value);
                return;
            }
        }
        nodeMatrix = new MatrixElem(i, j, value);
        list.add(nodeMatrix);
    }

    public void delete(int i, int j) {
        MatrixElem nodeMatrix;
        for (int k = 0; k < list.size; k++) {
            nodeMatrix = list.get(k);
            if (nodeMatrix.getI() == i && nodeMatrix.getJ() == j) {
                list.delete(k);
                break;
            }
        }
    }

    public int sum() {
        int sum = 0;
        for (int k = 0; k < list.size; k++) {
            MatrixElem nodeMatrix = list.get(k);
            if (nodeMatrix.getI() == nodeMatrix.getJ()) {
                sum += nodeMatrix.getValue();
            }
        }
        return sum;
    }
}