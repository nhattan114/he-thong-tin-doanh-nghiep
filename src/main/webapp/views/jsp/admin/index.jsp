<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <div class="container-fluid">
     <div class="row">
         <div class="col-12 col-md-10 offset-md-1">
             <div class="profile-info">
                 <h1 class="text-center">Quản lý tài khoản chủ sân</h1>
                 <div class="info-body">
                     <button class="btn btn-outline-primary" type="button" data-toggle="modal" data-target="#add-account-modal">
                         <i class='bx bxs-add-to-queue'></i> Thêm Mới
                     </button>
                     <div class="table-wrapper-scroll-y">
                         <table class="table table-bordered table-hover mt-4 table-responsive-md">
                             <thead>
                                 <tr>
                                     <th>ID</th>
                                     <th>Username</th>
                                     <th>Họ tên</th>
                                     <th>CMND</th>
                                     <th>Số điện thoại</th>
                                     <th>Action</th>
                                 </tr>
                             </thead>
                             <!-- CONTENT -->
                             <tbody id="table-content">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>
                                        <a class="btn btn-sm btn-primary" href="#>"><i class="fa-solid fa-eye"></i></a>
                                    </td>
                                </tr>
                             </tbody>
                         </table>
                     </div>
                 </div>
             </div>
         </div>
     </div>
 </div>

 <!-- ADD ACCOUNT MODAL -->
 <div class="modal" id="add-account-modal">
     <div class="modal-dialog modal-lg modal-dialog-centered">
         <div class="modal-content">
             <div class="modal-header">
                 <h3 class="modal-title text-primary">Thêm tài khoản chủ sân</h3>
                 <button type="button" class="close" data-dismiss="modal">
                     &times;
                 </button>
             </div>
             <div class="modal-body">
                 <form method="" action="POST">
                     <div class="form-group">
                         <label for="userName">Username:</label>
                         <input class="form-control" id="userName" type="text">
                     </div>
                     <div class="form-group">
                         <label for="firstName">Tên:</label>
                         <input class="form-control" id="firstName" type="text">
                     </div>
                     <div class="form-group">
                         <label for="lastName">Họ và tên đệm:</label>
                         <input class="form-control" id="lastName" type="text">
                     </div>
                     <div class="form-group">
                         <label for="sex">Giới tính:</label>
                         <select class="form-control" name="sex" id="sex">
                            <option value="true" selected>Male</option>
                            <option value="false">Female</option>
                         </select>
                     </div>
                     <div class="form-group">
                         <label for="birthDay">Birthday:</label>
                         <input class="form-control" id="birthDay" type="date">
                     </div>
                     <div class="form-group">
                         <label for="sdt">Số điện thoại:</label>
                         <input class="form-control" id="sdt" type="text">
                     </div>
                     <div class="form-group">
                         <label for="cmnd">CMND:</label>
                         <input class="form-control" id="cmnd" type="text">
                     </div>
                     <div class="form-group">
                         <div id="fail-alert" class="alert alert-danger mt-2" style="opacity: 0; display:none">
                             This type of file is not allowed
                         </div>
                         <div id="success-alert" class="alert alert-success mt-2" style="opacity: 0; display:none;">
                             This type of file is allowed
                         </div>
                     </div>
                 </form>
             </div>
             <div class="modal-footer">
                 <button type="submit" id="add-account-btn" name="submit" class="btn btn-sm btn-primary p-2">Confirm</button>
             </div>
         </div>
     </div>
 </div>
 
 <!--Disapprove MODAL -->
<div class="modal" tabindex="-1" role="dialog" id="delete-modal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Xác nhận từ chối hóa đơn</h5>
        <button
          type="button"
          class="close"
          data-dismiss="modal"
          aria-label="Close"
        >
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>
            Bạn có chắc là bạn muốn từ chối đơn đặt sân của <span id="delete-name">Hoàng</span> không?
        </p>
        <div class="form-group">
          <div
            id="fail-alert2"
            class="alert alert-danger mt-2"
            style="opacity: 0; display: none"
          >
            This type of file is not allowed
          </div>
          <div
            id="success-alert2"
            class="alert alert-success mt-2"
            style="opacity: 0; display: none"
          >
            This type of file is allowed
          </div>
        </div>
      </div>

      <div class="modal-footer">
        <a>
          <button
            type="button"
            class="btn btn-danger"
            id="delete-btn"
          >
            Confirm
          </button>
        </a>

        <button
          type="button"
          class="btn btn-secondary"
          data-dismiss="modal"
        >
          Close
        </button>
      </div>
    </div>
  </div>
</div>
 
 <script src="js/admin/main.js"></script>