public class Agent_graph {
    public int[][][][] rela = new int[10][10][10][10];

    public Agent_graph(){



        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                    for(int k=0;k<10;k++)
                    {
                        for(int z=0;z<10;z++) {
                            int loss = (int) (Math.random()*10);
                            rela[i][j][k][z] = loss;
                        }
                    }
            }
        }
    }
    public  int[][] get_rela(int i,int j)
    {
        int [][]now_rela=rela[i][j];
        return now_rela;
    }

    public int get_loss(int id,int neibour,int value_self,int value_neibour){

        return rela[id][neibour][value_neibour][value_self];
    }


}
