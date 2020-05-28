public class HelloNumbers{
	public static void main(String[] args){
		for(int i = 0; i < 10; i++){
			int num = 0;
			for(int j = 0; j <= i; j++){
				num += j;
			}
			if (i < 9)
				System.out.print(num + " ");
			else
				System.out.println(num);
		}
	}
}