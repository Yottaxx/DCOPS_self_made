public class Main {
    public static void main(String[] args) {
        Thread mythreads = new Mythread("0");
        mythreads.start();
        Thread mythreads1 = new Mythread("1");
        mythreads1.start();
        Thread mythreads2 = new Mythread("2");
        mythreads2.start();
        Thread mythreads3 = new Mythread("3");
        mythreads3.start();
        Thread mythreads4 = new Mythread("4");
        mythreads4.start();
        Thread mythreads5 = new Mythread("5");
        mythreads5.start();
        Thread mythreads6 = new Mythread("6");
        mythreads6.start();
        Thread mythreads7= new Mythread("7");
        mythreads7.start();
        Thread mythreads8 = new Mythread("8");
        mythreads8.start();
        Thread mythreads9 = new Mythread("9");
        mythreads9.start();
    }
}
