import java.util.Arrays;

public class Mythread extends Thread{

    public static Message message1=new Message();
    public static Message message2=new Message();
    public static int Up_down;
    public static int []value_path=new int[10];
    public static Agent_graph agent_graph=new Agent_graph();
    public static Data data=new Data();
    //public static int value=0;
    public static int time=0;
    public static final int length_domain=10;
    public static int now_situa=0;
    public static int stop=0;
    public static int loss=0;
    //public static  int times=0;

    public  Mythread(String name)
    {

        this.setName(name);
    }

    @Override
    public void run() {
        System.out.println("thiread "+this.getName()+"is running");
        int []value=new int[1];
        value[0]=-2;
        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';
        int []get_next_time=new int[1];
        get_next_time[0]=1;
        int []value_list=new int[10];
        for(int i=0;i<10;i++)
            value_list[i]=i;
        //System.out.println(Arrays.toString(value_list));
        while(stop!=1) {
            if (this.getName() == "0" && time == 0) {
                time++;
                //get_next_value(value,value_list, get_next_time);
                get_next_time[0]++;
                value[0]=0;
                intiate();
                sendFirstMessage(value);

            } else {
                if (message1.getReciver() == I_name){
                    if (message1.getSender() > I_name) {
                        receive_from_previous(value, value_list, get_next_time, 1);
                    } else {
                        receive_from_next(value, value_list, get_next_time, 1);
                        get_next_time[0] = 0;
                    }
            }
                if (message2.getReciver() == I_name) {
                    if (message2.getSender() > I_name) {
                        receive_from_previous(value, value_list, get_next_time, 2);

                    } else {
                        receive_from_next(value, value_list, get_next_time, 2);
                        get_next_time[0] = 0;
                    }
                }
            }

            //int value=(int)(Math.random()%length_domain);
        }
        System.out.println(this.getName()+" "+Arrays.toString(value_path));
        System.out.println(Up_down);
    }

    private void receive_from_next(int []value,int []value_list,int []get_next_time,int me) {
        if(me==1)
            message1.re_Message();
        else
            message2.re_Message();
        get_next_value(value,value_list,get_next_time);
        send_token(value,value_list,get_next_time);
    }

    private void send_token(int[]value,int []value_list,int[] get_next_time) {
        System.out.println("thiread "+this.getName()+"send_token");
        if(value[0]!=-1)
        {
            System.out.println("now_situa"+now_situa);
            if(now_situa==9)
            {
                int nine_loss=loss;
                value_path[9]=value[0];
                loss=loss+new_loss(value);
                Up_down=loss;

                while(value[0]!=-1)
                {
                    value[0]=get_next_value(value,value_list,get_next_time);
                    if(nine_loss+new_loss(value)<Up_down)
                    {
                        Up_down=nine_loss+new_loss(value);
                        value_path[9]=value[0];
                    }
                    if(Up_down==0)
                        stop=1;//terminated
                }
                send_previous_Message();

            }
            else
                sendMessage(value);
        }
        else
        {
            if(now_situa==0)
                stop=1;
            else
                send_previous_Message();
        }

    }

    private void send_previous_Message() {//loss not change only sendmessage change loss
        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';

        now_situa=I_name-1;

        value_path[I_name]=-1;
        System.out.println("send_previous_message"+this.getName()+"now_situa"+now_situa);
        message1.setReciver(I_name-1);
        message1.setSender(I_name);
        message1.setValue(-1);


    }

    private void receive_from_previous(int[] value,int []value_list,int[] get_next_time,int me) {


        if(me==1)
            message1.re_Message();
        else
            message2.re_Message();

        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';
        value_path[I_name]=-1;//delete previous path

        get_next_value(value,value_list,get_next_time);

        send_token(value,value_list,get_next_time);


    }

    private void sendFirstMessage(int []value) {
        System.out.println("thiread "+this.getName()+"send_First");
        now_situa=1;
        if(value[0]!=-1)
        {
            value_path[0]=value[0];
            if(data.num[0].left_loss!=-1)
            {
                message1.setReciver(data.num[0].left_loss);
                message1.setSender(0);
                message1.setValue(value[0]);
            }
            if(data.num[0].right_loss!=-1)
            {
                message2.setReciver(data.num[0].right_loss);
                message2.setSender(0);
                message2.setValue(value[0]);
            }
            loss=0;
        }
        else {
            stop=1;
        }

    }

    private void sendMessage(int []value) {

        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';

        if(value[0]!=-1)
        {
            value_path[I_name]=value[0];
            if(data.num[I_name].left_loss!=-1)
            {
                message1.setReciver(data.num[I_name].left_loss);
                message1.setSender(I_name);
                message1.setValue(value[0]);
            }
            if(data.num[I_name].right_loss!=-1)
            {
                message2.setReciver(data.num[I_name].right_loss);
                message2.setSender(I_name);
                message2.setValue(value[0]);
            }
            now_situa=I_name+1;
            loss=loss+new_loss(value);
        }
       else
           System.out.println("wrong for sendmessage");

    }

    private int new_loss(int []value) {
        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';
        int loss=0;
        for(int i=0;i<now_situa-1;i++)
        {
            System.out.println("i for newloss"+i+"id"+this.getName());
            if(data.num[i].right_loss==I_name)
            {
                loss+=agent_graph.get_loss(I_name,i,value_path[i],value[0]);
            }
            else if(data.num[i].left_loss==I_name)
            {
                loss+=agent_graph.get_loss(I_name,i,value_path[i],value[0]);
            }
        }
        return loss;
    }
    private int new_loss(int temp_value) {
        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';
        int loss=0;

        for(int i=0;i<now_situa-1;i++)
        {
            if(data.num[i].right_loss==I_name)
            {
                System.out.println(I_name+" "+i+" "+value_path[i]+" "+temp_value);
                loss+=agent_graph.get_loss(I_name,i,value_path[i],temp_value);

            }
            else if(data.num[i].left_loss==I_name)
            {
                System.out.println(I_name+" "+i+" "+value_path[i]+" "+temp_value);
                loss+=agent_graph.get_loss(I_name,i,value_path[i],temp_value);
            }
        }
        System.out.println("newloss"+loss+" "+this.getName());
        return loss;
    }



    public void intiate()
    {
    for(int i=0;i<10;i++) {
        value_path[i] = -1;//null;
    }
    Up_down=100000;
    }

    public int get_next_value(int[] value,int []value_list,int[] get_next_time) {

        get_next_time[0]++;

        if(get_next_time[0]==10)//null le
            return -1;
        else {
            for (int i = get_next_time[0] - 1; i < 10; i++) {
                if (value_list[i] == i) {
                    if (check(i) == 1) {
                        value[0] = i;
                        System.out.println("return"+this.getName()+"value "+value[0]);
                        return i;

                    }

                }

            }
        }
        System.out.println("setnull"+this.getName());
        value[0] = -1;
        return -1;//null
    }

    public int check(int temp_value)  {
        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';
        int can=1;
        if(now_situa==0)
            return 1;
        for(int i=0;i<Math.max(now_situa,I_name);i++)
        {
            if(value_path[i]==-1)
                 can=0;
        }
        while(can==0){
          can=Cempty();
        }

        if(can==1) {
            if ((loss + new_loss(temp_value)) < Up_down)
                return 1;
            else
                return 0;
        }
        return 0;
    }

    private int Cempty() {
        char string_name=this.getName().charAt(0);
        int I_name=string_name-'0';
        int can=1;
        for(int i=0;i<Math.max(now_situa,I_name);i++)
        {
            if(value_path[i]==-1)
                can=0;
        }
        return can;
    }
}
