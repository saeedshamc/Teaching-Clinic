// RMILegacyDemo.java — RMI concepts برای فهم (فقط توضیح — RMI server نیاز به registry دارد)
// این فایل فقط مفاهیم را نشان می‌دهد — برای اجرای واقعی نیاز به RMI registry است
import java.rmi.Remote;
import java.rmi.RemoteException;

// Remote interface — methods that can be called remotely
interface HelloService extends Remote {
    String sayHello() throws RemoteException;
}

// Server implementation — extends UnicastRemoteObject
// class HelloImpl extends UnicastRemoteObject implements HelloService {
//     public HelloImpl() throws RemoteException { super(); }
//     public String sayHello() { return "Hello from RMI server!"; }
// }

// Client usage:
// HelloService stub = (HelloService) Naming.lookup("rmi://localhost/HelloService");
// String result = stub.sayHello();

public class RMILegacyDemo {
    public static void main(String[] args) {
        System.out.println("RMI Legacy Demo");
        System.out.println("RMI is deprecated for new projects — use REST/gRPC instead");
        System.out.println("This file shows concepts only — actual RMI requires registry setup");
    }
}
