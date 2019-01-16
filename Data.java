public class Data {
    public  Leef []num=new Leef[10];
    public Data()
    {
        for(int i=0;i<10;i++)
            num[i]=new Leef();

        num[0].left=num[1];
        num[0].right=num[2];

        num[0].left_loss=1;
        num[0].right_loss=2;


        num[1].left=num[2];
        num[1].right=num[3];
        num[1].left_loss=2;
        num[1].right_loss=3;


        num[2].left=num[3];
        num[2].left_loss=3;

        num[3].left=num[4];
        num[3].right=num[6];
        num[3].left_loss=4;
        num[3].right_loss=6;


        num[4].left=num[7];
        num[4].left_loss=7;
        num[4].right=num[5];
        num[4].right_loss=5;

        num[5].left=num[6];
        num[5].left_loss=6;
        num[6].left=num[7];
        num[6].left_loss=7;

        num[7].left=num[8];
        num[7].left_loss=8;
        num[8].left=num[9];
        num[8].left_loss=9;
    }




}
