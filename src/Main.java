import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        long p,q,m,n,b;

        p=59;
        q=37;
        m=p*q;
        n=p*q*(p-1)*(q-1);
        System.out.println("");
        System.out.println("p="+p+" ; q="+q);
        System.out.println("m="+m+" ; n="+n);

        System.out.println("Выберите beta из ниже указанных");
        for (int i=1;Math.pow(2,i)<p;i++){
            System.out.print(i+" ;");
        }
        b=p;
        while (Math.pow(2,b)>=p){
            b=sc.nextInt();
            if (Math.pow(2,b)<p)
                System.out.println("Выбранное b="+b);
            else
                System.out.println("Неверное ");
        }
        int k=(int)Math.pow(2,b);
        long alpha = ((p-1)*(q-1)*(p-k)*(q-k))/(k);
        System.out.println("Alpha = "+ alpha);
        System.out.println("Выберите простое число ню, чтобы оно было меньше Alpha");
        long nu = sc.nextLong();

        long ksi = inverse(nu,alpha);
        System.out.println("Кси = "+ksi);

        System.out.println("Закрывающий ключ - " +nu+ " , "+ n);
        System.out.println("Открывающий ключ - " +ksi+ " , "+ m);

        System.out.println("Шифруем данные");
        System.out.println("Введеите число для зашифровки");
        long M = sc.nextLong();
        long C = mod(M,nu,n);
        System.out.println("Шифрованные данные - "+ C);
        M = mod(C,ksi,m);
        System.out.println("Обратная расшифровка - "+M);
    }


    public static long NOD(long a,long b)
    {
        while(a!=0 && b!=0)
        {
            if(a>=b) a=a%b;
            else b=b%a;
        }
        return a+b; // Одно - ноль
    }

    public static long inverse(long a,long n)
    {
        long x = extended_euclid(a, n);
        if (NOD(a,n) == 1) return x;
        return 0;

    }
    public static long extended_euclid(long a, long b){
        long q, r, x1, x2, y1, y2;
        long x,y;
        if (b == 0) {
            //d = a; x = 1;y = 0;
            return 1;
        }
        x2 = 1;
        x1 = 0;
        y2 = 0;
        y1 = 1;
        while (b > 0) {
            q = a / b;
            r = a - q * b;
            x = x2 - q * x1;
            y = y2 - q * y1;
            a = b;
            b = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
        }
        x = x2;
        return  x;
    }
    public static long mod(long A , long e, long m){
        String strE = Long.toString(e,2);

        long R=1;


        for (int j=0;j<strE.length();j++){
            R= Math.floorMod((long)Math.pow(R,2),m);
            if (strE.charAt(j)=='1')
                R= Math.floorMod(R*A,m);
        }
        return R;
    }
}

