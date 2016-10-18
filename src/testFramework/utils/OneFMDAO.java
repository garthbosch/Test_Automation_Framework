package testFramework.utils;

import testFramework.enums.ProductType;
import testFramework.enums.SchemeOption;
import testFramework.objects.AppUsers;
import testFramework.objects.DailyRaising;
import testFramework.objects.LastDailyRaising;
import testFramework.objects.Product;
import testFramework.objects.Scheme;
import testFramework.objects.SchemeMembers;
import testFramework.objects.WorkflowData;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import testFramework.objects.DebitOrdersData;
import testFramework.testing.testSuites.BaseClass;

public class OneFMDAO extends BaseClass {

    private Connection conn;
    private Statement stmt;
    private CallableStatement callableStmt;
    private ResultSet rs;
    private Integer update;
    private Integer insert;
    private PreparedStatement prepStmt;
    private void initVar() {
        conn = null;
        stmt = null;
        rs = null;
        update = 0;
        insert = 0;
    }

    private void connectToDB(String env) throws Exception {
        DBConnections db = new DBConnections();
        if (env.equalsIgnoreCase("DEV")) {
            conn = db.getDEVConnection();
            System.out.println("[Info]**********DATABASE CONNECTION TO \'DEV\' WAS SUCCESSFUL**********");
        }
        if (env.equalsIgnoreCase("QA")) {
            conn = db.getQAConnection();
            System.out.println("[Info]**********DATABASE CONNECTION TO \'QA\' WAS SUCCESSFUL**********");
        }
    }

    private boolean closeDB() throws Exception {
        if (!DBConnections.close(rs)) {
            System.err.println("[Error] Result set was not closed.");
            return false;
        }
        if (!DBConnections.close(stmt)) {
            System.err.println("[Error] Statement was not closed.");
            return false;
        }
        if (!DBConnections.close(conn)) {
            System.err.println("[Error] Database Connection was not closed.");
            return false;
        }
        System.out.println("[Info]**********DATABASE CONNECTION CLOSE WAS SUCCESSFUL**********");
        return true;
    }

    public AppUsers validateUserAndGetPassword(String enteredUsername, String env) throws Exception {
        initVar();
        AppUsers user = new AppUsers();
        try {
            connectToDB(env.toString());
            String sql = "select username, password, role from RMM_BSSP_OneFM_App.wrk.testingusers where username = '" + enteredUsername + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
            System.out.println("[Info] Successful retrieval of user.");
            return user;
        } catch (Exception ex) {
            System.err.println("[Error] Unsuccessful retrieval of user.");
            return null;
        } finally {
            closeDB();
        }
    }

    public String addUser(String env, String username, String password, String role) throws Exception {
        initVar();
        int rowsInserted = 0;
        String message = "";
        try {
            connectToDB(env.toString());
            String sql = "select username from RMM_BSSP_OneFM_App.wrk.testingusers where username = '" + username + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                message = "User already exist!";
            } else {
                callableStmt = conn.prepareCall("EXECUTE [wrk].[AddUsers] ?,?,?");
                callableStmt.setString(1, username);
                callableStmt.setString(2, password);
                callableStmt.setString(3, role);
                rowsInserted = callableStmt.executeUpdate();
                message = "User " + username + " has been successfully added!";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return message;
    }

    public List<AppUsers> listOfUsers(String env) throws Exception {
        initVar();
        List<AppUsers> users = new ArrayList<AppUsers>();
        try {
            connectToDB(env.toString());
            String sql = "select username, password, role from RMM_BSSP_OneFM_App.wrk.testingusers";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AppUsers user = new AppUsers();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return users;
    }

    public AppUsers getUserDetails(String env, String username) throws Exception {
        initVar();
        AppUsers user = new AppUsers();
        try {
            connectToDB(env.toString());
            String sql = "select username, password, role from RMM_BSSP_OneFM_App.wrk.testingusers where username = '" + username + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
            }
            System.out.println("User " + user.getUsername() + " with password " + user.getPassword() + " and role " + user.getRole() + " was retrieved.");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return user;
    }

    public String updateUserDetails(String env, String username) throws Exception {
        initVar();
        String message = "", password = "", role = "";
        try {
            connectToDB(env.toString());
            String sql = "update RMM_BSSP_OneFM_App.wrk.testingusers set password = '" + password + "', role = '" + role + "' where username = '" + username + "'";
            stmt = conn.createStatement();
            update = stmt.executeUpdate(sql);
            if (update >= 0) {
                message = "success";
            } else {
                message = "unsuccessful";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return message;
    }

    public String getSchemeStatusWithSchemeNumber(String schemeNumber, String env) throws Exception {
        initVar();
        String status = "";
        try {
            connectToDB(env.toString());
            String sql = "select SchemeStatusDescription from [prd].[Scheme] where schemeNumber = '" + schemeNumber + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                status = rs.getString("SchemeStatusDescription");
            }
            System.out.println("[Info] The status of the scheme \"" + schemeNumber + "\" is " + status);
        } catch (Exception ex) {
            System.err.println("[Error] Unable to retrieve the status of the scheme \"" + schemeNumber + "\"");
        } finally {
            closeDB();
        }
        return status;
    }

    public Boolean isExistingCust(String id, String env) throws Exception {
        initVar();
        Boolean isCustNo = false;
        try {
            connectToDB(env.toString());
            String sql = "select IDNumber from [prd].[Member] where IDNumber = '" + id + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                isCustNo = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return isCustNo;
    }

    public List<String> getExistingCustIds(String schemeNo, String env) throws Exception {
        initVar();
        List<String> ids = new ArrayList<String>();
        try {
            connectToDB(env.toString());
            String sql = "select mem.IDNumber from [prd].[Member] mem join [prd].[SchemeMember] sm on mem.MemberId = sm.MemberID "
                    + "join [prd].[SchemeClient] sc on sm.SchemeClientID = sc.SchemeClientID join [prd].[Scheme] sch on sch.SchemeID = sc.SchemeID "
                    + "	group by mem.IDNumber, sch.SchemeNumber having sch.SchemeNumber = '" + schemeNo + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("IDNumber");
                ids.add(id);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return ids;
    }

    public Scheme getSchemeData(String schemeNo, String env) throws Exception {
        initVar();
        Scheme scheme = new Scheme();
        List<SchemeMembers> schemeMemberList = new ArrayList<SchemeMembers>();
        List<Product> schemeOptionList = new ArrayList<Product>();
        try {
            connectToDB(env.toString());
            String sql = "select "
                    //                    scheme columns
                    + "sch.SchemeID, sch.ProductTypeID, sch.SchemeNumber, sch.SchemeName, sch.SchemeStatusDescription, sch.LastChangedBy, "
                    + "sch.LastChangedDate, sch.LongRunningJob, "
                    //                    scheme member columns  
                    + "sm.SchemeClientID, sm.JoinDate, sm.MemberStatusDescription, sm.CoverCommencementDate, sm.MemberTypeID, sm.WaitingPeriod, "
                    + "sm.ExceptionInd, sm.IndTakeOnAge, "
                    //                    member columns 
                    + "mem.MemberID, mem.IDNumber, mem.TitleDescription, mem.FirstName, mem.Surname, mem.IDNumber, mem.DateofBirth, mem.GenderDescription "
                    + "from [prd].[Member] mem join [prd].[SchemeMember] sm on mem.MemberId = sm.MemberID "
                    + "join [prd].[SchemeClient] sc on sm.SchemeClientID = sc.SchemeClientID "
                    + "join [prd].[Scheme] sch on sch.SchemeID = sc.SchemeID "
                    + "group by sch.SchemeID, sch.ProductTypeID, sch.SchemeNumber, sch.SchemeName, sch.SchemeStatusDescription, sch.LastChangedBy, "
                    + "sch.LastChangedDate, sch.LongRunningJob, sm.SchemeClientID, sm.JoinDate, sm.MemberStatusDescription, sm.CoverCommencementDate, "
                    + "sm.MemberTypeID, sm.WaitingPeriod, "
                    + "sm.ExceptionInd, sm.IndTakeOnAge, mem.MemberID, mem.IDNumber, mem.TitleDescription, mem.FirstName, mem.Surname, mem.IDNumber, "
                    + "mem.DateofBirth, mem.GenderDescription having sch.SchemeNumber = '" + schemeNo + "'";
         
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("ProductTypeID") == 1) {
                    scheme.setProductTypeID(ProductType.BS);
                } else {
                    scheme.setProductTypeID(ProductType.GFS);
                }
                scheme.setSchemeId(rs.getString("SchemeID"));
                scheme.setSchemeNumber(rs.getString("SchemeNumber"));
                scheme.setSchemeName(rs.getString("SchemeName"));
                scheme.setSchemeStatusDescription(rs.getString("SchemeStatusDescription"));
                scheme.setLastChangedBy(rs.getString("LastChangedBy"));
                scheme.setLastChangedDate(rs.getTimestamp("LastChangedDate"));
                scheme.setLongRunningJob(rs.getString("LongRunningJob"));
                SchemeMembers schemeMem = new SchemeMembers();
                schemeMem.setMemberId(rs.getString("MemberID"));
                schemeMem.setTitleDescription(rs.getString("TitleDescription"));
                schemeMem.setFirstName(rs.getString("FirstName"));
                schemeMem.setSurname(rs.getString("Surname"));
                schemeMem.setIdNumber(rs.getString("IDNumber"));
                schemeMem.setDob(rs.getString("DateofBirth"));
                schemeMem.setGenderDescription(rs.getString("GenderDescription"));
                schemeMem.setSchemeClientId(rs.getString("SchemeClientID"));
                schemeMem.setJoinDate(rs.getString("JoinDate"));
                schemeMem.setMemberStatusDescription(rs.getString("MemberStatusDescription"));
                schemeMem.setCoverCommencementDate(rs.getString("CoverCommencementDate"));
                schemeMem.setMemberTypeId(rs.getInt("MemberTypeID"));
                schemeMem.setWaitingPeriod(rs.getInt("WaitingPeriod"));
                schemeMem.setExceptionInd(rs.getString("ExceptionInd"));
                schemeMem.setIndTakeOnAge(rs.getInt("IndTakeOnAge"));
                schemeMemberList.add(schemeMem);
                scheme.setSchemeMembers(schemeMemberList);
//                adding the product 
                String sqlProducts = "select so.ProductID, p.ProductCode, so.WaitingPeriod, so.WaitingPeriod2 "
                        + "from prd.SchemeOptions so join prd.Product p on so.ProductID = p.ProductID "
                        + "group by so.ProductID, p.ProductCode, so.WaitingPeriod, so.WaitingPeriod2, so.SchemeID "
                        + "having so.SchemeID = '" + scheme.getSchemeId() + "'";
//                stmt = conn.createStatement();
                rs = null;
                rs = stmt.executeQuery(sqlProducts);
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getString("ProductID"));
                    product.setProductCode(rs.getString("ProductCode"));
                    product.setWaitingPeriod(rs.getInt("WaitingPeriod"));
                    product.setWaitingPeriod2(rs.getInt("WaitingPeriod2"));
                    schemeOptionList.add(product);
                    scheme.setProducts(schemeOptionList);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return scheme;
    }

    public Boolean setGFSSchemeStatus(Scheme scheme, String env) throws Exception {
        initVar();
        int rowsInserted = 0;
        try {
            connectToDB(env.toString());
            scheme.setLongRunningJob(null);
            scheme.setLastChangedBy("OneFM");
            scheme.setLastChangedDate(seleniumDriver.getDateAndTimeInDateFormat(0, 0, 0, 0, 0, 0));
            System.out.println("Todays Date: " + seleniumDriver.getDateDashFormat(0, 0, 0));
            callableStmt = conn.prepareCall("EXECUTE [prd].[SetSchemeStatusBasedOnAction] ?,?,?,?");
            callableStmt.setString(1, scheme.getSchemeId());
            callableStmt.setString(2, "Accept");
            callableStmt.setString(3, scheme.getLastChangedBy());
            callableStmt.setDate(4, Date.valueOf(seleniumDriver.getDateDashFormat(0, 0, 0)));
            rowsInserted = callableStmt.executeUpdate();
            System.out.println("[Info] The scheme \"" + scheme.getSchemeName() + "\" has been successfully updated");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] The scheme \"" + scheme.getSchemeName() + "\" was NOT change - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }

    public Boolean setBSSPSchemeStatus(Scheme scheme, String env) throws Exception {
        initVar();
        int rowsInserted = 0;
        try {
            connectToDB(env.toString());
            scheme.setLongRunningJob(null);
            scheme.setLastChangedBy("OneFM");
            scheme.setLastChangedDate(seleniumDriver.getDateAndTimeInDateFormat(0, 0, 0, 0, 0, 0));
            String prevStatus = scheme.getSchemeStatusDescription();
            scheme.setSchemeStatusDescription("Awaiting First Premium");
            // cascade scheme status to policies
            callableStmt = conn.prepareCall("EXECUTE [prd].[CascadeSchemeStatusChange] ?,?,?");
            callableStmt.setString(1, scheme.getSchemeId());
            callableStmt.setString(2, prevStatus);
            callableStmt.setString(3, scheme.getSchemeStatusDescription());
            rowsInserted = callableStmt.executeUpdate();
            List<SchemeMembers> schemeMemList = new ArrayList<SchemeMembers>();
            List<Product> productList = new ArrayList<Product>();
            // update waiting periods
            for (int i = 0; i < scheme.getSchemeMembers().size(); i++) {
                // check for single member
                if (scheme.getProducts().get(i).getProductCode().contains(SchemeOption.SingleMemberAdvance.getProductCode())
                        || scheme.getProducts().get(i).getProductCode().contains(SchemeOption.SingleMemberDeLux.getProductCode())) {
                    int wp = (scheme.getSchemeMembers().get(i).getIndTakeOnAge() == null ? 0 : 0) <= 65 ? scheme.getProducts().get(i).getWaitingPeriod() : scheme.getProducts().get(i).getWaitingPeriod2();
                    SchemeMembers sm = new SchemeMembers();
                    sm.setWaitingPeriod(wp);
                    scheme.setSchemeMembers(schemeMemList);
                    continue;
                }
                SchemeMembers sm = new SchemeMembers();
                sm.setWaitingPeriod(scheme.getProducts().get(i).getWaitingPeriod());
                int smWaitingPeriod = scheme.getSchemeMembers().get(i).getMemberTypeId() == 4 ? scheme.getProducts().get(i).getWaitingPeriod2() : scheme.getProducts().get(i).getWaitingPeriod();
                sm.setWaitingPeriod(smWaitingPeriod);
                schemeMemList.add(sm);
                scheme.setSchemeMembers(schemeMemList);
            }
//           setting long running job of scheme to have the recalculate done
            scheme.setLongRunningJob("Recalc");
            String sql = "update prd.Scheme set LongRunningJob = '" + scheme.getLongRunningJob() + "' where schemeId = " + scheme.getSchemeId();
            stmt = null;
            update = null;
            stmt = conn.createStatement();
            update = stmt.executeUpdate(sql);
//             create the first welcome pack
            callableStmt = null;
            callableStmt = conn.prepareCall("EXECUTE [wrk].[CreateFirstWelcomePack] ?");
            callableStmt.setString(1, scheme.getSchemeId());
            rowsInserted = callableStmt.executeUpdate();
            System.out.println("[Info] Scheme " + scheme.getSchemeName() + " has been successfully updated");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] Scheme " + scheme.getSchemeName() + " has been NOT been successfully updated - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }

    public boolean setSchemeStatusToCancel(Scheme scheme, String env) throws Exception {
        initVar();
        try {
            connectToDB(env.toString());
            callableStmt = conn.prepareCall("EXECUTE [prd].[CancelScheme] ?,?,?");
            callableStmt.setString(1, scheme.getSchemeId());
            callableStmt.setString(2, scheme.getLastChangedBy());
            callableStmt.setDate(3, Date.valueOf(seleniumDriver.getDateDashFormat(0, 0, 0)));
            update = callableStmt.executeUpdate();
            System.out.println("[Info] Scheme " + scheme.getSchemeName() + " status has been successfully updated to Cancel");
            update = 0;
            scheme.setLongRunningJob("NULL");
            String sql = "update prd.Scheme SET LongRunningJob = '" + scheme.getLongRunningJob() + "' WHERE SchemeID = '" + scheme.getSchemeId() + "'";
            prepStmt = conn.prepareStatement(sql);
            update = prepStmt.executeUpdate();
            System.out.println("[Info] Scheme " + scheme.getSchemeName() + " LongRunningJob was successfully updated to NULL - rows updated " + update);
        } catch (Exception ex) {
            System.err.println("[Error] Scheme " + scheme.getSchemeName() + " status has NOT been successfully updated to Cancel - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
        return true;
    }

    public WorkflowData getWorkflowDataToWakeUpItems(String env, Scheme scheme) throws Exception {
        initVar();
        WorkflowData wd = new WorkflowData();
        try {
            connectToDB(env.toString());
            String sql = "select do.DebitOrderID, do.WorkflowItemID, do.PaymentType, wi.StepID, wi.StatusID, wi.WakeUpOn, wi.PriorityID, wi.Pended, wi.ProcessID "
                    + "from wrk.DebitOrders do JOIN wrk.WorkItem wi ON do.WorkflowItemID = wi.ItemID "
                    + "JOIN wrk.StepStatus ss ON wi.StepID = ss.StepID and wi.StatusID = ss.StatusID "
                    + "JOIN wrk.Priority p on wi.PriorityID = p.PriorityID "
                    + "group by do.DebitOrderID, do.WorkflowItemID, do.PaymentType, wi.StepID, ss.StepID, wi.StatusID, ss.StatusID, wi.WakeUpOn, "
                    + "wi.PriorityID, p.PriorityID, wi.Pended, wi.ItemID, do.SchemeNumber, do.SchemeID, wi.ProcessID "
                    + "HAVING do.SchemeID = '" + scheme.getSchemeId() + "' AND wi.Pended = 1"; //and wi.WakeUpOn <= GETDATE()
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                wd.setDebitOrderId(rs.getString("DebitOrderID"));
                wd.setWorkflowItemId(rs.getInt("WorkflowItemID"));
                wd.setPaymentType(rs.getString("PaymentType"));
                wd.setStatusId(rs.getInt("StatusID"));
                wd.setStepId(rs.getInt("StepID"));
                wd.setWakeUpOn(rs.getTimestamp("WakeUpOn"));
                wd.setPriorityId(rs.getInt("PriorityID"));
                wd.setPended(rs.getInt("Pended"));
                wd.setProcessId(rs.getInt("ProcessID"));
            }
            System.out.println("[Info]************GETTING WORKFLOW DATA TO WAKE UP ITEMS START*********************************");
            System.out.println("Debit Order Id: " + wd.getDebitOrderId() + " \n"
                    + "Workflow Item Id: " + wd.getWorkflowItemId() + " \n"
                    + "Payment Type: " + wd.getPaymentType() + " \n"
                    + "Status Id: " + wd.getStatusId() + " \n"
                    + "Step Id: " + wd.getStepId() + " \n"
                    + "Wake up on: " + wd.getWakeUpOn());
            System.out.println("[Info]************GETTING WORKFLOW DATA TO WAKE UP ITEMS FINISH********************************");

        } catch (Exception ex) {
            System.err.println("[Error] Unsuccessful retrieval of Workflow items while waking up items - " + ex.getMessage());
        } finally {
            closeDB();
        }
        return wd;
    }

    public Boolean unPendWorkItem(Integer workflowItemId, String env) throws Exception {
        initVar();
        int rowsInserted = 0;
        try {
            connectToDB(env.toString());
            callableStmt = conn.prepareCall("EXECUTE [wrk].[UnPendWorkItem] ?,?");
            callableStmt.setInt(1, workflowItemId);
            callableStmt.setString(2, "OneFM Selenium Testing");
            rowsInserted = callableStmt.executeUpdate();
            System.out.println("[Info] Unpending of work item \'" + workflowItemId + "\' was SUCCESSFUL!");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] Unpending of work item \'" + workflowItemId + "\' was UNSUCCESSFUL - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }

    public List<WorkflowData> getWorkflowDataToProcessQueues(String env, WorkflowData wd) throws Exception {
        initVar();
        List<WorkflowData> wds = new ArrayList<>();
        try {
            connectToDB(env.toString());
            String sql = "select wi.ItemID, wi.StepID, wi.StatusID, wi.PriorityID, wi.ProcessID, q.QueueID, q.AutomatedQueue, ss.StatusAction "
                    + "from  wrk.WorkItem wi JOIN wrk.StepStatus ss ON wi.StepID = ss.StepID AND wi.StatusID = ss.StatusID "
                    + "JOIN wrk.ProcessStep ps ON wi.ProcessID = ps.ProcessID "
                    + "JOIN wrk.Queue q on ps.QueueID = q.QueueID "
                    + "JOIN wrk.Priority p ON wi.PriorityID = p.PriorityID "
                    + "group by wi.ItemID, wi.StepID, wi.StatusID, wi.PriorityID, wi.ProcessID, q.QueueID, q.AutomatedQueue, ss.StatusAction "
                    + "HAVING wi.ItemID = '" + wd.getWorkflowItemId() + "' AND q.AutomatedQueue = 1";// and ss.StatusAction != NULL AND ss.StatusAction != ''";

            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                WorkflowData wdata = new WorkflowData();
                wdata.setWorkflowItemId(rs.getInt("ItemID"));
                wdata.setStatusId(rs.getInt("StatusID"));
                wdata.setStepId(rs.getInt("StepID"));
                wdata.setPriorityId(rs.getInt("PriorityID"));
                wdata.setProcessId(rs.getInt("ProcessID"));
                wdata.setQueueId(rs.getInt("QueueID"));
                wdata.setAutomatedQueue(rs.getInt("AutomatedQueue"));
                wdata.setStatusAction(rs.getString("StatusAction"));
                wds.add(wdata);
            }
            System.out.println("[Info]************GETTING WORKFLOW DATA TO PROCESS QUEUES START*********************************");
            for (WorkflowData workflowData : wds) {
                System.out.println("Workflow Item Id: " + workflowData.getWorkflowItemId() + " \n"
                        + "Payment Type: " + workflowData.getPaymentType() + " \n"
                        + "Status Id: " + workflowData.getStatusId() + " \n"
                        + "Step Id: " + workflowData.getStepId());
            }
            System.out.println("[Info]************GETTING WORKFLOW DATA TO PROCESS QUEUES ITEMS FINISH********************************");

        } catch (Exception ex) {
            System.err.println("[Error] Unsuccessful retrieval of Workflow items while processing queues - " + ex.getMessage());
        } finally {
            closeDB();
        }
        return wds;
    }

    public boolean processQueues(Integer workflowItemId, String env) throws Exception {
        initVar();
        int rowsInserted = 0;
        try {
            connectToDB(env.toString());
            callableStmt = conn.prepareCall("EXECUTE [wrk].[PaymentReceived] ?");
            callableStmt.setInt(1, workflowItemId);
            rowsInserted = callableStmt.executeUpdate();
            System.out.println("[Info] Workflow item \'" + workflowItemId + "\' queue was successfully processed!");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] Workflow item \'" + workflowItemId + "\' queue was NOT processed - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }

    public WorkflowData getWorkflowDataToAdvanceItems(String env, WorkflowData wd) throws Exception {
        initVar();
        WorkflowData wdata = new WorkflowData();
        try {
            connectToDB(env.toString());
            String sql = "select wi.ItemID, wi.StepID, wi.StatusID, wi.WakeUpOn, wi.PriorityID, wi.Pended, wi.ProcessID, ps.QueueID, q.AutomatedQueue, "
                    + "ss.FinalStatus, ps.NextStepID, do.DateRaisedFor "
                    + "from wrk.WorkItem wi JOIN wrk.ProcessStep ps ON wi.StepID = ps.StepID AND wi.ProcessID = ps.ProcessID "
                    + "JOIN wrk.StepStatus ss ON wi.StepID = ss.StepID and wi.StatusID = ss.StatusID "
                    + "JOIN wrk.Priority p ON wi.PriorityID = p.PriorityID "
                    + "JOIN wrk.Queue q ON ps.QueueID = q.QueueID "
                    + "JOIN wrk.DebitOrders do ON wi.ItemID = do.WorkflowItemID "
                    + "group by wi.ItemID, wi.StepID, ss.StepID, wi.StatusID, ss.StatusID, wi.WakeUpOn, "
                    + "wi.PriorityID, p.PriorityID, wi.Pended, wi.ItemID, wi.ProcessID, ps.QueueID, q.QueueID, q.AutomatedQueue, ss.FinalStatus, "
                    + "ps.NextStepID, do.DateRaisedFor "
                    + "HAVING wi.ItemID = '" + wd.getWorkflowItemId() + "' AND wi.StepID != 4 AND wi.Pended = 0 AND ss.FinalStatus = 1 and q.AutomatedQueue = 1 "
                    + "AND ps.NextStepID != ''";
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                wdata.setWorkflowItemId(rs.getInt("ItemID"));
                wdata.setStatusId(rs.getInt("StatusID"));
                wdata.setStepId(rs.getInt("StepID"));
                wdata.setWakeUpOn(rs.getTimestamp("WakeUpOn"));
                wdata.setPriorityId(rs.getInt("PriorityID"));
                wdata.setPended(rs.getInt("Pended"));
                wdata.setProcessId(rs.getInt("ProcessID"));
                wdata.setQueueId(rs.getInt("QueueID"));
                wdata.setAutomatedQueue(rs.getInt("AutomatedQueue"));
                wdata.setFinalStatus(rs.getInt("FinalStatus"));
                wdata.setDateRaisedFor(rs.getTimestamp("DateRaisedFor"));
            }
            System.out.println("[Info]************GETTING WORKFLOW DATA TO ADVANCE ITEMS START*********************************");
            System.out.println("Workflow Item Id: " + wdata.getWorkflowItemId() + " \n"
                    + "Payment Type: " + wdata.getPaymentType() + " \n" + "Status Id: " + wdata.getStatusId() + " \n"
                    + "Step Id: " + wdata.getStepId() + " \n" + "Priority Id: " + wdata.getPriorityId() + " \n"
                    + "Pended : " + wdata.getPended() + " \n" + "Process Id: " + wdata.getProcessId() + " \n"
                    + "Queue Id: " + wdata.getQueueId() + " \n" + "AutomatedQueue Id: " + wdata.getAutomatedQueue() + " \n"
                    + "Final Status: " + wdata.getFinalStatus() + " \n" + "Date Raised For: " + wdata.getDateRaisedFor());
            System.out.println("[Info]************GETTING WORKFLOW DATA TO ADVANCE ITEMS FINISH********************************");

        } catch (Exception ex) {
            System.err.println("[Error] Get Work Data To Advance Itmes was not successfully executed - " + ex.getMessage());
        } finally {
            closeDB();
        }
        return wdata;
    }

    public boolean advanceWorkItemStep(Integer workflowItemId, String workflowService, java.util.Date baseDate, Integer dbStepId, String env) throws Exception {
        initVar();
        int rowsInserted = 0;
        int stepId = 0;
        System.out.println("Base Date: " + baseDate);
        String newDate = String.valueOf("");
        try {
            connectToDB(env.toString());
            stepId = getStepId(workflowItemId);
            int i = 0;
            while (stepId != dbStepId) {
                callableStmt = conn.prepareCall("EXECUTE [wrk].[AdvanceWorkItemStep] ?,?,?");
                callableStmt.setInt(1, workflowItemId);
                callableStmt.setString(2, workflowService);
                callableStmt.setString(3, newDate);
                rowsInserted = callableStmt.executeUpdate();
                stepId = getStepId(workflowItemId);
                System.out.println(i + ". StepId = " + stepId);
                i++;
            }
            System.out.println("[Info] Advanced work item \'" + workflowItemId + "\' successfully!");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] Advancing of work item \'" + workflowItemId + "\' was unsuccessful - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }

    private Integer getStepId(Integer workflowItemId) throws SQLException {
        int stepId = 0;
        String sql = "SELECT StepID, StatusID FROM wrk.WorkItem WHERE ItemID = '" + workflowItemId + "'";
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            stepId = rs.getInt("StepID");
        }
        return stepId;
    }

    public boolean doAdvancePaymentForDebitOrderScheme(String schemeNo, String env) throws Exception {
        initVar();
        int rowsInserted = 0;
        try {
            connectToDB(env.toString());
            callableStmt = conn.prepareCall("EXECUTE [wrk].[InsertOutstandingPREMforPointInTimeForMultipleSchemes] ?");
            callableStmt.setString(1, schemeNo);
            rowsInserted = callableStmt.executeUpdate();
            System.out.println("[Info] An advance premium payment for scheme \'" + schemeNo + "\' was SUCCESSFULLY completed!");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] An advance premium payment for scheme \'" + schemeNo + "\' was UNSUCCESSFULLY completed - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }

    public DailyRaising getRaisingForScheme(String schemeNumber, String env) throws Exception {
        initVar();
        DailyRaising raising = new DailyRaising();
        try {
            connectToDB(env.toString());
            String sql = "select s.SchemeID, s.SchemeStatusDescription, bd.BankAccNo, bd.BankSortCode, "
                    + "s.SchemeStatusDescription, s.PaymentMethodDescription, sc.Contribution, s.MoneyDeductionDay "
                    + "from prd.Scheme s JOIN prd.SchemeBankDetails sb on s.SchemeID = sb.SchemeID "
                    + "JOIN prd.BankDetail bd ON sb.BankDetailID = bd.BankDetailID "
                    + "JOIN prd.BankAccType ba ON bd.BankAccTypeID = ba.BankAccTypeID "
                    + "JOIN prd.SchemeClient sc ON s.SchemeID = sc.SchemeID "
                    + "GROUP BY s.SchemeNumber, s.SchemeID, s.SchemeStatusDescription, bd.BankAccNo,"
                    + "bd.BankSortCode, s.SchemeStatusDescription, s.PaymentMethodDescription, sc.Contribution, s.MoneyDeductionDay "
                    + "HAVING s.SchemeNumber = '" + schemeNumber + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                raising.setSchemeId(rs.getInt("SchemeID"));
                raising.setSchemeNumber(schemeNumber);
                raising.setDeductionDay(rs.getInt("MoneyDeductionDay"));
                raising.setStatus(rs.getString("SchemeStatusDescription"));
                raising.setAmountDue(rs.getDouble("Contribution"));
                raising.setBankAccountNumber(rs.getString("BankAccNo"));
                raising.setDateRaised(seleniumDriver.getDateAndTimeInDateFormat(0, 0, 0, 0, 0, 0));
                raising.setDateRaisedFor(seleniumDriver.getDateAndTimeInDateFormat(0, 0, 0, 0, 0, 0));
                raising.setBankSortCode(rs.getString("BankSortCode"));
                raising.setPaymentType(rs.getString("PaymentMethodDescription"));
                raising.setYmActionInd("");
                raising.setYmInstructionType("PREM");
                raising.setUserId("oneFM");
                raising.setLatestInd(Short.valueOf("1"));
                raising.setYmType("");
                raising.setYmOnly(false);
            }
            System.out.println("************RAISING ENTRY ADDITION*********************************");
            System.out.println("Scheme Id: " + raising.getSchemeId() + " \n"
                    + "Scheme Number: " + raising.getSchemeNumber() + " \n"
                    + "Deduction day: " + raising.getDeductionDay() + " \n"
                    + "Status: " + raising.getStatus() + " \n"
                    + "Amount Due: " + raising.getAmountDue() + " \n"
                    + "Bank Acc Number: " + raising.getBankAccountNumber() + " \n"
                    + "Bank Sort Code : " + raising.getBankSortCode() + " \n"
                    + "Payment Type: " + raising.getPaymentType() + " \n"
                    + "YM Instruction Type: " + raising.getYmInstructionType() + " \n"
                    + "User Id: " + raising.getUserId() + " \n"
                    + "Latest Ind: " + raising.getLatestInd() + " \n"
                    + "YM Only: " + raising.isYmOnly());
            System.out.println("************RAISING ENTRY ADDITION FINISH********************************");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return raising;
    }

    public Integer createWorkItem(DailyRaising raising, String env) throws Exception {
        initVar();
        String key = "SchemeID, int, " + raising.getSchemeId() + "; DateRaised, smalldatetime, "
                + raising.getDateRaised() + "; RD, char(1), N;";
        Integer itemId = null;
        Integer parentItemId = null;
        try {
            connectToDB(env.toString());
            callableStmt = conn.prepareCall("EXECUTE [wrk].[WorkItemCreate] ?,?,?,?,?");
            callableStmt.setString(1, "Receipts");
            callableStmt.setString(2, key);
            callableStmt.setString(3, "oneFM");
            callableStmt.setInt(4, 0);
            callableStmt.registerOutParameter(5, java.sql.Types.INTEGER);
            update = callableStmt.executeUpdate();
            itemId = callableStmt.getInt(5);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return itemId;
    }

    public String getScheduleParameterValue(String env) throws Exception {
        initVar();
        String value = "";
        try {
            connectToDB(env.toString());
            String sql = "SELECT Value from prd.ScheduleParameter where Id = 1";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                value = rs.getString("Value");
            }
            System.out.println("[Info] ScheduleParameter value field was successfully returned.");
        } catch (Exception ex) {
            System.err.println("[Error] ScheduleParameter value was not successfully returned - " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return value;
    }

    public void setScheduleParameterValue(String env, String value) throws Exception {
        initVar();
        try {
            connectToDB(env.toString());
            String sql = "update prd.ScheduleParameter SET Value = '" + value + "' where Id = 1";
            stmt = conn.createStatement();
            update = stmt.executeUpdate(sql);
            System.out.println("[Info] Rows updated " + update + ".");
            System.out.println("[Info] ScheduleParameter value was updated to " + value + ".");
        } catch (Exception ex) {
            System.err.println("[Error] ScheduleParameter value was not successfully set - " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeDB();
        }
    }

    public String getPaymentDay(Parameter parameter, String env) throws Exception {
        initVar();
        String dateRaisingFor = "";
        String deductionDay = null;
        try {
            connectToDB(env.toString());
            String sql1 = "SELECT value from [prd].[ScheduleParameter] where id = 1";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql1);
//            getting the date value from the parameter table
            while (rs.next()) {
                dateRaisingFor = rs.getString("value");
                System.out.println("[Info] Date Value from ScheduleParameter table is: " + dateRaisingFor);
            }
            stmt = null;
            rs = null;
            String sql2 = "Select top 1 deductionDay FROM [wrk].[YMRaisingSchedule] where YMRaisingDate = '" + dateRaisingFor + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql2);
            while (rs.next()) {
                int day = rs.getInt("deductionDay");
                deductionDay = String.valueOf(day);
            }
            System.out.println("[Info] Successful retrieval of Deduction Day: " + deductionDay);
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while trying to return the deduction day value - " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return deductionDay == null ? parameter.getSchemePaymentMethodPaymentDay() : deductionDay;
    }

    public boolean updateDebitOrderEntryForRD(String env, String schemeNumber, String rdReason) throws Exception {
        initVar();
        int rowsInserted = 0;
        try {
            connectToDB(env.toString());

            callableStmt = conn.prepareCall("EXECUTE [wrk].[UpdateSchemeDebitOrderEntryForRD_AutomatedTesting] ?, ?");
            callableStmt.setString(1, schemeNumber);
            callableStmt.setString(2, rdReason);
            rowsInserted = callableStmt.executeUpdate();
            System.out.println("[Info] A RD for scheme \'" + schemeNumber + "\' was successfully completed!");
        } catch (Exception ex) {
            System.err.println("[Error] Debit Order Entry not update for RD. An error occurred - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
        return true;
    }

    public boolean cascadeStatus(String env, String schemeNumber, String schemeStatusDescription) throws Exception {
        initVar();
        int rowsInserted = 0;
        try {
            connectToDB(env.toString());
            callableStmt = conn.prepareCall("EXECUTE [wrk].[CascadeStatus_AutomatedTesting] ?, ?");
            callableStmt.setString(1, schemeNumber);
            callableStmt.setString(2, schemeStatusDescription);
            rowsInserted = callableStmt.executeUpdate();
            System.out.println("[Info] Rows updated: " + rowsInserted);
            System.out.println("[Info] Scheme \'" + schemeNumber + "\' was successfully put into a/n " + schemeStatusDescription + " status");
        } catch (Exception ex) {
            System.err.println("[Error] Cascade Status was unsuccessful. An error occurred - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
        return true;
    }

    public List<String> getNextScheduledRaisingDateForScheme(String env, Parameter parameter, String schemeNumber, Integer rowsToReturn) throws Exception {
        initVar();
        List<String> nextScheduledRaisingDates = new ArrayList<>();
        Date joinedDate = null;
        int moneyDeductionDay = 0;
        Date YMRaisingDate = null;
        try {
            connectToDB(env.toString());
            String sql1 = "select JoinedDate, MoneyDeductionDay FROM prd.Scheme where SchemeNumber = '" + schemeNumber + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql1);
//            getting the date value from the parameter table
            while (rs.next()) {
                joinedDate = rs.getDate("JoinedDate");
                moneyDeductionDay = rs.getInt("MoneyDeductionDay");
                System.out.println("[Info] Joined Date for scheme \"" + schemeNumber + "\" is \"" + joinedDate + "\"");
                System.out.println("[Info] Money Deduciton Day for scheme \"" + schemeNumber + "\" is \"" + moneyDeductionDay + "\"");
            }
            stmt = null;
            rs = null;
            String sql2 = "select TOP " + rowsToReturn + " YMRaisingDate from wrk.YMRaisingSchedule where YMRaisingDate > CONVERT(char, "
                    + "(select JoinedDate FROM prd.Scheme where SchemeNumber = '" + schemeNumber + "')) AND DeductionDay = "
                    + "(select MoneyDeductionDay FROM prd.Scheme where SchemeNumber = '" + schemeNumber + "')";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql2);
            while (rs.next()) {
                YMRaisingDate = rs.getDate("YMRaisingDate");
                System.out.println("[Info] The next Scheduled YMRaising Date (in date format) for scheme \"" + schemeNumber + "\" is \"" + YMRaisingDate + "\"");
                String nextScheduledRaisingDate = rs.getString("YMRaisingDate");
                System.out.println("[Info] The next Scheduled YMRaising Date (in string format) for scheme \"" + schemeNumber + "\" is \"" + nextScheduledRaisingDate + "\"");
                nextScheduledRaisingDates.add(nextScheduledRaisingDate);
            }
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while trying to return the next Scheduled YMRaising Date - " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeDB();
        }
        return nextScheduledRaisingDates;
    }

    public boolean updatePremiumCollectionItem(String schemeNumber, String env) throws Exception {
        initVar();
        try {
            connectToDB(env.toString());
            String sql = "update wrk.PremiumCashCollection SET DateUpdated = DateReceived "
                    + "WHERE SchemeID = (SELECT SchemeID FROM prd.Scheme where SchemeNumber = '" + schemeNumber + "')";
            stmt = conn.createStatement();
            update = stmt.executeUpdate(sql);
            System.out.println("[Info] \"" + update + "\" Rows successfully updated in the PremiumCollection table for scheme \"" + schemeNumber + "\"");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to update the PremiumCollection table - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }

    public String checkIfSelectedParameterValueExistOnYMRaisingScheduleTable(String env, String scheduleParameterValue) throws Exception {
        initVar();
        String ymRaisingDate = "";
        try {
            connectToDB(env.toString());
            rs = getResultSet(scheduleParameterValue);
            int d = 0;
            while (!rs.next()) {
//                setting the ymRaisingDate to one month back 
                System.out.println("[Warning] There was no YMRaising Date for this day: \"" + scheduleParameterValue + "\"");
                rs = getResultSet(seleniumDriver.getDateWithYearFist(d++, -2, 0));
            }
            ymRaisingDate = rs.getString("YMRaisingDate");
            System.out.println("[Info] The YMRaisingDate \"" + ymRaisingDate + "\" value will be used on the ScheduleParameter table.");

        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to retrieve the YMRaisingDate value - " + ex.getMessage());
        } finally {
            closeDB();
        }
        return ymRaisingDate;
    }

    private ResultSet getResultSet(String scheduleParameterValue) throws SQLException {
        String sql = "select YMRaisingDate from wrk.YMRaisingSchedule where YMRaisingDate = '" + scheduleParameterValue + "'";
        ResultSet rSet = conn.createStatement().executeQuery(sql);
        return rSet;
    }

    public DebitOrdersData getSchemePremiumsRaised(String env, String schemeNumber, String dateRaised) throws Exception {
        initVar();
        DebitOrdersData debitOrder = new DebitOrdersData();
        try {
            connectToDB(env.toString());
            String sql = "SELECT DebitOrderID, SchemeID, SchemeNumber, AmountDue, DateRaised, DateRaisedFor, PaymentType "
                    + "FROM wrk.DebitOrders where SchemeNumber = '" + schemeNumber + "'";
            rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                debitOrder.setDebitOrderId(rs.getString("DebitOrderID"));
                debitOrder.setSchemeId(rs.getString("SchemeID"));
                debitOrder.setSchemeNumber(rs.getString("SchemeNumber"));
                debitOrder.setAmountDue(rs.getString("AmountDue").replace(".", "").trim());
                debitOrder.setDateRaised(rs.getTimestamp("DateRaised"));
                debitOrder.setDateRaisedFor(rs.getTimestamp("DateRaisedFor"));
                debitOrder.setPaymentType(rs.getString("PaymentType"));
            }
            System.out.println("[Info]************Retrieving Debit Order Details for Scheme Daily Raising");
            System.out.println("Debit Order Id: " + debitOrder.getDebitOrderId());
            System.out.println("Scheme Id: " + debitOrder.getSchemeId());
            System.out.println("Scheme Number: " + debitOrder.getSchemeNumber());
            System.out.println("Amount: " + debitOrder.getAmountDue());
            System.out.println("Date Raised: " + debitOrder.getDateRaised());
            System.out.println("Date Raised For: " + debitOrder.getDateRaisedFor());
            System.out.println("Payment Type: " + debitOrder.getPaymentType());
            System.out.println("[Info]************End Of Retrieving Debit Order Details for Scheme Daily Raising");
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to retrieve the debit order details - " + ex.getMessage());
        } finally {
            closeDB();
        }
        return debitOrder;
    }

    public String getYMoutputLocation(String env) throws Exception {
        initVar();
        String defaultYMoutputLocation = null;
        try {
            connectToDB(env.toString());
            String sql = "select value FROM prd.ScheduleParameter where id = 4";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                defaultYMoutputLocation = rs.getString("value");
            }
            System.out.println("[Info] ScheduleParameter value for id 4 is \"" + defaultYMoutputLocation + "\"");
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to get the ScheduleParameter value from the database - " + ex.getMessage());
        } finally {
            closeDB();
        }
        return defaultYMoutputLocation;
    }

    public boolean setYMoutputLocation(String env, String ymOutputLocation) throws Exception {
        initVar();
        try {
            connectToDB(env.toString());
            String sql = "UPDATE prd.ScheduleParameter SET value = '" + ymOutputLocation + "' WHERE Id = 4";
            stmt = conn.createStatement();
            update = stmt.executeUpdate(sql);
            System.out.println("[Info] \"" + update + "\" Rows successfully updated in the ScheduleParameter table with value \"" + ymOutputLocation + "\"");
            return true;
        } catch (Exception ex) {
            System.err.println("[Error] An error occurred while attempting to update the value field in the ScheduleParameter table - " + ex.getMessage());
            return false;
        } finally {
            closeDB();
        }
    }
}
