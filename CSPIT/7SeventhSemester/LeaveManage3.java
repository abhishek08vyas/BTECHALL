
/**
 
 * @author 
 
 *
 
 */
 
 
 
 
 class Employee extends Login {
 
    
 
            private int nLeave=0;
 
            
 
            // Set Login flag
 
            public void SetLoginFlag(boolean IsLogin){
 
                super.SetLoginFlag(IsLogin);
 
            }
 
            // Get Login Flag
 
            public boolean GetLoginFlag() {
 
                return super.GetLoginFlag();
 
            }
 
            
 
            // Set user type
 
            public void SetUserType(EnLoginUserType enUserType) {
 
                super.SetUserType(enUserType);
 
            }
 
            // Get user type
 
            public EnLoginUserType getUserType() {
 
                return super.getUserType();
 
            }
 
            
 
            // Set user id
 
            public void SetUserId(String sUserId){
 
                super.SetUserId(sUserId);
 
            }
 
            // Get user id
 
            public String GetUserId() {
 
                return super.GetUserId();
 
            }
 
            
 
            // Set password
 
            public void SetPassword(String sPassword){
 
                super.SetPassword(sPassword);
 
            }
 
            // Get password
 
            public String GetPassword() {
 
                return super.GetPassword();
 
            }
 
            
 
            public int GetLeaveTotal(){
 
                return 10;
 
            }
 
            
 
            // Set leave
 
            public void SetLeave(int nLeave) {
 
                this.nLeave=nLeave;
 
            }
 
            
 
            // Get leave
 
            public int GetLeave() {
 
                return this.nLeave;
 
            }
 
}
 
 
 
 abstract class Login {
 
 
    /*----------Member variable----------*/
 
    // Store login true or false
 
    private boolean bIsLogin = false;
 
    // Store user type
 
    private EnLoginUserType enUserType = EnLoginUserType.Admin;
 
    // Store user id
 
    private String sUserid = "";
 
    // Store password
 
    private String sPassword = "";
 
 
    /*----------Property----------*/
 
    
 
    // Set Login flag
 
    protected void SetLoginFlag(boolean IsLogin){
 
        this.bIsLogin=IsLogin;
 
    }
 
    // Get Login Flag
 
    protected boolean GetLoginFlag() {
 
        return this.bIsLogin;
 
    }
 
    
 
    // Set user type
 
    protected void SetUserType(EnLoginUserType enUserType) {
 
        this.enUserType = enUserType;
 
    }
 
    // Get user type
 
    protected EnLoginUserType getUserType() {
 
        return this.enUserType;
 
    }
 
    
 
    // Set user id
 
    protected void SetUserId(String sUserId){
 
        this.sUserid=sUserId;
 
    }
 
    // Get user id
 
    protected String GetUserId() {
 
        return this.sUserid;
 
    }
 
    
 
    // Set password
 
    protected void SetPassword(String sPassword){
 
        this.sPassword=sPassword;
 
    }
 
    // Get password
 
    protected String GetPassword() {
 
        return this.sPassword;
 
    }
 
}
 
 
 
 class Admin extends Login  {
 
    
 
        // Set Login flag
 
        public void SetLoginFlag(boolean IsLogin){
 
            super.SetLoginFlag(IsLogin);
 
        }
 
        // Get Login Flag
 
        public boolean GetLoginFlag() {
 
            return super.GetLoginFlag();
 
        }
 
        
 
        // Set user type
 
        public void SetUserType(EnLoginUserType enUserType) {
 
            super.SetUserType(enUserType);
 
        }
 
        // Get user type
 
        public EnLoginUserType getUserType() {
 
            return super.getUserType();
 
        }
 
        
 
        // Set user id
 
        public void SetUserId(String sUserId){
 
            super.SetUserId(sUserId);
 
        }
 
        // Get user id
 
        public String GetUserId() {
 
            return super.GetPassword();
 
        }
 
        
 
        // Set password
 
        public void SetPassword(String sPassword){
 
            super.SetPassword(sPassword);
 
        }
 
        // Get password
 
        public String GetPassword() {
 
            return super.GetPassword();
 
        }
 
}
 
 
 class ReportingAuthority extends Login {
 
    // Set Login flag
 
            public void SetLoginFlag(boolean IsLogin){
 
                super.SetLoginFlag(IsLogin);
 
            }
 
            // Get Login Flag
 
            public boolean GetLoginFlag() {
 
                return super.GetLoginFlag();
 
            }
 
            
 
            // Set user type
 
            public void SetUserType(EnLoginUserType enUserType) {
 
                super.SetUserType(enUserType);
 
            }
 
            // Get user type
 
            public EnLoginUserType getUserType() {
 
                return super.getUserType();
 
            }
 
            
 
            // Set user id
 
            public void SetUserId(String sUserId){
 
                super.SetUserId(sUserId);
 
            }
 
            // Get user id
 
            public String GetUserId() {
 
                return super.GetPassword();
 
            }
 
            
 
            // Set password
 
            public void SetPassword(String sPassword){
 
                super.SetPassword(sPassword);
 
            }
 
            // Get password
 
            public String GetPassword() {
 
                return super.GetPassword();
 
            }
 
}
 
 
public class LeaveManage {
 
    private static Admin m_adminUser=null;
 
    private static Employee m_empUser=null;
 
    private static ReportingAuthority m_RAUser=null;
 
    private static Employee[] lstEmpInfo=new Employee[15];
 
    private static ReportingAuthority[] lstRAInfo=new ReportingAuthority[15];
 
    private static int countRA=-1; 
 
        private static int countEmp=-1; 
 
 
    /**
 
     * @param args
 
     */
 
    public static void main(String[] args) {
 
    LoginScreen();
 
    }
 
    
 
    public static void LoginScreen(){
 
    System.out.println("-------------------------------------------------");
 
    System.out.println("\t\tLogin");
 
    System.out.println("-------------------------------------------------");
 
    System.out.println("1.Admin");
 
    System.out.println("2.Employee");
 
    System.out.println("3.RA");
 
    Scanner input=new Scanner(System.in);
 
    System.out.print("Enter option:");
 
    int choice=input.nextInt();
 
    switch(choice){
 
        case 1:
 
        AdminScreen();
 
    break;
 
    case 2:
 
    EmployeeScreen();
 
    case 3:
 
    RAScreen();
 
    default:
 
    System.out.println("Invalid choice");
 
    }
 
    LoginScreen();
 
    }
 
    
 
    public static void RAScreen(){
 
        Scanner input=new Scanner(System.in);
 
        System.out.print("Enter userid: ");
 
        String userid=input.nextLine();
 
        System.out.print("Enter password:");
 
        String pass=input.nextLine();
 
        for(int i=0;i<lstRAInfo.length;i++){
 
            if(lstRAInfo[i].GetUserId().equals(userid)==true&&lstRAInfo[i].GetPassword().equals(pass)==true){
 
                Scanner Tempinput=new Scanner(System.in);
 
                System.out.println("-------------------------------------------------");
 
        System.out.println("RA HOME");
 
        System.out.println("-------------------------------------------------");
 
        while(true) {
 
            System.out.println("1.Report");
 
            System.out.println("2.Accept/reject");
 
            System.out.println("3.Logout");
 
            System.out.print("Enter option:");
 
            int choice=Tempinput.nextInt();
 
            switch(choice){
 
                        case 1:
 
                        for(int j=0;j<lstEmpInfo.length;j++){
 
                            userid=Tempinput.nextLine();
 
                            if(userid==lstEmpInfo[i].GetUserId())
 
                            System.out.println("No. of leave remaining:"+lstEmpInfo[i].GetLeave());
 
                        }
 
                            
 
                        break;
 
                    case 2:
 
                   
                        System.out.println("Enter userid:");
                        for(int j=0;j<lstEmpInfo.length;j++){
 
                            String Empuserid=Tempinput.nextLine();
 
                            if(Empuserid.equals(lstEmpInfo[i].GetUserId())==true){
                                System.out.println("Requested leave is"+lstEmpInfo[i].GetLeave());
                                System.out.print("Enter accept leaves:");
 
                                int leave=Tempinput.nextInt();
 
                                
 
                                int countleave=10-leave;
 
                                lstEmpInfo[i].SetLeave(countleave);
 
                            }
 
                        }
 
                        break;
 
                        case 3:
 
                        LoginScreen();
 
                    default:
 
                    System.out.println("Invalid choice");
 
                    }
 
        }
 
        
 
        }
 
        }
 
    }
 
    
 
    public static void EmployeeScreen(){
 
        
 
        Scanner input=new Scanner(System.in);
 
        System.out.print("Enter userid:");
 
        String userid=input.nextLine();
 
        System.out.print("Enter password:");
 
        String pass=input.nextLine();
 
        for(int i=0;i<lstEmpInfo.length;i++){
 
            if(lstEmpInfo[i].GetUserId().equals(userid)==true&&lstEmpInfo[i].GetPassword().equals(pass)==true){
 
                System.out.println("-------------------------------------------------");
 
        System.out.println("\tEmployee HOME");
 
        System.out.println("-------------------------------------------------");
 
        while(true) {
 
            System.out.println("1.Report");
 
            System.out.println("2.Apply leave");
 
            System.out.println("3.Logout");
 
            System.out.print("Enter option:");
 
            int choice=input.nextInt();
 
            switch(choice){
 
                        case 1:
 
                            System.out.println("No. of leave remaining:"+lstEmpInfo[i].GetLeave() );
 
                        break;
 
                    case 2:
 
                        System.out.print("Enter no of leave taken:");
 
                        int leave=input.nextInt();
 
                        int countleave=lstEmpInfo[i].GetLeave()-leave;
 
                        lstEmpInfo[i].SetLeave(countleave);
 
                        break;
 
                        case 3:
 
                        LoginScreen();
 
                        break;
 
                    default:
 
                    System.out.println("Invalid choice");
 
                    }
 
        }
 
 
        }
 
        }
 
    }
 
    
 
    public static void AdminScreen(){
 
        Scanner input=new Scanner(System.in);
 
        System.out.print("Enter userid: ");
 
        String userid=input.nextLine();
 
        System.out.print("Enter password:");
 
        String pass=input.nextLine();
 
        
 
        if(userid.equals("Admin")==true&&pass.equals("Admin123")==true){
 
            Scanner Tempinput=new Scanner(System.in);
 
            System.out.println("-------------------------------------------------");
 
        System.out.println("\t\tAdmin HOME");
 
        System.out.println("-------------------------------------------------");
 
        while(true) {
 
            System.out.println("1.Add employee");
 
            System.out.println("2.Add RA");
 
            System.out.println("3.Logout");
 
            System.out.print("Enter option:");
 
            int choice=input.nextInt();
 
                    switch(choice){
 
                        case 1:
 
                        System.out.print("Enter userid:");
 
                        String empuserid=Tempinput.nextLine();
 
                        System.out.print("Enter password:");
 
                        String empass=Tempinput.nextLine();
 
                        System.out.print("Enter Reporting authority userid:");
 
                        String RAuserid=Tempinput.nextLine();
 
                        m_RAUser=new ReportingAuthority();
 
                        for(int i=0;i<lstRAInfo.length;i++){
 
                            if(lstRAInfo[i]==null|| lstRAInfo[i].GetUserId()!=RAuserid){
 
                                System.out.print("RA user not exist enter Reporting authority password:");
 
                                String RApass=Tempinput.nextLine();
 
                                m_RAUser.SetUserId(RAuserid);
 
                                m_RAUser.SetPassword(RApass);
 
                                break;
 
                            }
 
                        }
 
                        countRA++;
 
                        countEmp++;
 
                        lstRAInfo[countRA]=m_RAUser;
 
                        m_empUser=new Employee();
 
                        m_empUser.SetUserId(empuserid);
 
                        m_empUser.SetPassword(empass);
 
                        m_empUser.SetLeave(10);
 
                        lstEmpInfo[countEmp]=m_empUser;
 
                    break;
 
                    case 2:
 
                    System.out.print("Enter userid:");
 
                        userid=Tempinput.nextLine();
 
                        System.out.print("Enter password:");
 
                        pass=Tempinput.nextLine();
 
                        m_RAUser=new ReportingAuthority();
 
                        m_RAUser.SetUserId(userid);
 
                        m_RAUser.SetPassword(pass);
 
                        System.out.print("Enter list userid of employee(Separated by comma):");
 
                        String Empuserid=Tempinput.nextLine();
 
                        String[] arrOfStr = Empuserid.split(","); 
 
                        m_empUser=new Employee();
 
                        for(int i=0;i<lstEmpInfo.length;i++){
 
                            for(int j=0;j<arrOfStr.length;j++){
 
                                if(lstEmpInfo[i]==null||lstEmpInfo[i].GetUserId()!=arrOfStr[j]){
 
                                System.out.print("Emp user not exist enter Emp password:");
 
                                String Empass=Tempinput.nextLine();
 
                                m_empUser.SetUserId(arrOfStr[j]);
 
                                m_empUser.SetPassword(Empass);
 
                                m_empUser.SetLeave(10);
 
                                break;
 
                            }
 
                            }
 
                        }
 
                        countRA++;
 
                        countEmp++;
 
                        lstRAInfo[countRA]=m_RAUser;
 
                        lstEmpInfo[countEmp]=m_empUser;
 
                        break;
 
                        case 3:
 
                        LoginScreen();
 
                    default:
 
                    System.out.println("Invalid choice");
 
                    }
 
        }
 
        
 
        }
 
    }
 
}

