package setting.export;

public class Emp {

 private String empNo;
 private String empName;
 private String birthday;
 private String year;
 private String tel;

 public void setEmpNo (String no){
	 empNo = no;
 }

 public void setEmpName (String name){
	 empName = name;
 }

 public void setTel (String telephone){
	 tel = telephone;
 }
 
 public void setYear (String years){
	 year = years;
 }
 
 public void setBirthday (String bir){
	 birthday = bir;
 }
 
 public String getEmpNo (){
	 return empNo;
 }
 
 public String getEmpName (){
	 	 return empName;
 }
 
 public String getTel (){
 	 return tel;
 }
 
 public String getYear (){
 	 return year;
 }
 
 public String getBirthday (){
 	 return birthday;
 }
}