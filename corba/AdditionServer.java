import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

class AdditionServer {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Create the implementation object
            AdditionImpl additionImpl = new AdditionImpl();

            // Get reference to the root POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            // Activate the servant with the POA
            org.omg.CORBA.Object ref = rootPOA.servant_to_reference(additionImpl);
            AdditionModule.Addition additionRef = AdditionModule.AdditionHelper.narrow(ref);

            // Get reference to the Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Bind the servant to the Naming Service
            String name = "Addition";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, additionRef);

            System.out.println("Addition Server is ready.");

            // Wait for invocations
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

