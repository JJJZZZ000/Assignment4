package Question2;

public class test_q2 {
    public static void main(String[] args){
        // remember to create an argument line;
        if(args.length == 0){
            System.out.println("Please enter the file!");
            return;
        }
        String fileName = args[0];
        boolean isValid = ValidGroup.isValid(fileName);
        System.out.println(isValid);
    }
}
