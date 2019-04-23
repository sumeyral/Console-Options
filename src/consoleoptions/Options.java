package consoleoptions;
/* @author sumeyral

 */

import java.util.Scanner;

public class Options {
    public static void main(String [] args)
    {
        Scanner kb = new Scanner(System.in);

        System.out.println("Aşağıdaki seçeneklerden birini girerek istenen işlemi yapabilirsiniz:");
        System.out.println("[1] En iç parantezde yer alan yazıyı getirir");
        System.out.println("[2] Yazıyı tek karakterden başlayarak alt alta basar");
        System.out.println("[3] Girilen iki yazıdan, ikinci içindeki karakterleri birinciden siler ve basar");
        System.out.println("[4] Girilen yazıdaki karakterlerin boyutlarını tersler");

        System.out.print("Seçiminiz:");
        int opt = Integer.parseInt(kb.nextLine());
        StringUtil.choice(opt);
    }
}
class StringUtil {
    private static final Scanner ms_kb;
    private static String m_str;

    static {ms_kb = new Scanner(System.in);
            }

    public static void choice(int val)
    {
        if(val != 1 || val != 2 || val != 3 || val != 4 )
            System.out.println("Hatalı seçenek numarası girdiniz");
        else {
            System.out.println("Yazıyı girin:");
            m_str = ms_kb.nextLine();

            switch (val) {
                case 1:
                    findInner(m_str);
                    break;
                case 2:
                    decompose(m_str);
                    break;
                case 3:
                    System.out.println("İkinci yazıyı da girin:");
                    String s = ms_kb.nextLine();
                    squeeze(m_str, s);
                    break;
                case 4:
                    changeCase(m_str);
                    break;
            }
        }

    }

    public static void changeCase(String s)
    {
        String chars = "abcçdefgğhıijklmnoöprsştuüvyz";
        String capitals = "ABCÇDEFGĞHIİJKLMNOÖPRSŞTUÜVYZ";
        String str = "";
        int len = chars.length();

        for(int i=0; i<s.length(); ++i) {
            for(int j=0; j<len; ++j)
                if(s.charAt(i) == chars.charAt(j))
                    str+= Character.toUpperCase(s.charAt(i));
                else if(s.charAt(i) == capitals.charAt(j))
                    str+= Character.toLowerCase(s.charAt(i));
        }

        System.out.println(str);
    }

    public static void decompose(String s)
    {
        for(int i=0; i<=s.length();++i){
            if(i==0)
                continue;
            System.out.println(s.substring(0,i));
        }
    }

    public static void findInner(String s)
    {
        int indexs = 0, indexe = 0, i;

        for(i=0; i<s.length();++i)
            if(s.charAt(i)=='{')
                indexs=i;

        for(i=indexs; i<s.length();++i)
            if(s.charAt(i)=='}') {
                indexe = i;
                break;
            }

        if(indexe != 0)
            System.out.println(s.substring(indexs+1, indexe));
        else
            System.out.println("Yanlış bir giriş!");
    }

    public static void squeeze(String str, String s)
    {
        s= s.toLowerCase();
        String temp = str.toLowerCase();
        str ="";

        for(int i=0;i<s.length();++i) {
            if(temp.contains(s.charAt(i)+"")) {
                for(int j=0; j<temp.length();++j) {
                    if(temp.charAt(j) == s.charAt(i))
                        continue;
                    str+=temp.charAt(j);
                }
                temp = str;
                str="";
            }
        }

        System.out.println(temp);
    }
}