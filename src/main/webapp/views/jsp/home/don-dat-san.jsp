<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="don-dat-san"></div>
<div class="container-fluid">
  <div class="row">
    <div class="col-12 col-md-10 offset-md-1">
      <div class="">
        <h1 class="text-center text-primary mb-3">Lịch sử đặt sân</h1>
        <div class="info-body">
            <h3>Số tiền cần thanh toán: <span id="payment" class="text-success"></span></h3>
          <div class="table-wrapper-scroll-y">
            <table
              class="table table-bordered table-hover mt-4 table-responsive-md"
            >
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Tên sân bóng</th>
                  <th>Địa chỉ</th>
                  <th>Giá</th>
                  <th>Khung giờ</th>
                  <th>Trạng thái</th>
                  <th>Ngày tạo</th>
                  <th>Action</th>
                </tr>
              </thead>
              <!-- CONTENT -->
              <tbody id="table-content">
                  <!<!-- render by ajax -->
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
