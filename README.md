# he-thong-tin-doanh-nghiep
(Để chạy chương trình thì thầy có thể sử dụng apache netbeans version 13)

1. Import cơ sở dữ liệu trên Microsoft SQL Server Management Studio
	- Chuột phải vào Database -> Import Data-tier Application...
	- Cửa sổ hiện lên -> Next -> Import DATSANBONG.bacpac trong file -> Next tiếp tục để import database vào hệ thống
	- Database đã được import thành công
2. Kết nối dữ liệu:
	- Trong project có file Connect.java trong package cd.cnpm.main.dao, trong đó sửa lại giá trị của username, password
để kết nối với CSDL và tên database đã thay đổi(nếu có).

3. Chuột phải và project và chọn Clean and Build để netbeans build project

4. Sau khi đã build xong thì chạy chương trình bằng cách chuột phải vào MainApplication.java trong package cd.cnpm.main -> Run File

5. Sau khi application đã chạy thành công thì thầy truy cập vào url 127.0.0.1 để truy cập vào web site.


Cung cấp một sô account để test chức năng của hệ thông:
	- tk: rachdia - pw: 123456 (chủ sân);
	- tk: daihoccanhsat - pw: 123456 (chủ sân);
	- tk: duchoang - pw: 123456 (khách hàng);
	- tk: longviet - pw: 123456 (khách hàng);
	- tk: admin - pw: admin123 (admin);

Cảm ơn thầy, chúc thầy một ngày vui vẻ.
