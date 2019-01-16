public class Leef {


    public Leef left;
    public Leef right;
    public int left_loss;
    public int right_loss;
    public int data;

    public  Leef(int data){
        this.data=data;
        this.left=null;
        this.right=null;
        right_loss=-1;
        left_loss=-1;

    }
    public  Leef()
    {
        this.data=0;
        this.left=null;
        this.right=null;
        right_loss=-1;
        left_loss=-1;
    }
    public void display()
    {
        System.out.print(data+" ");
    }

    public void setData(int data) {
        this.data = data;
    }




}
