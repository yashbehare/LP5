import org.omg.CORBA.*;
import org.omg.CosNaming.*;

class AdditionClient {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get reference to the Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the servant from the Naming Service
            String name = "Addition";
            AdditionModule.Addition additionRef = AdditionModule.AdditionHelper.narrow(ncRef.resolve_str(name));

            // Perform addition
            float num1 = 3.14f;
            float num2 = 2.71f;
            float result = additionRef.add(num1, num2);

            System.out.println("Addition Result: " + num1 + " + " + num2 + " = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

