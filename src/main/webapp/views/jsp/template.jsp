<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Trang profile</title>
    <!-- Bootstrap & Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link
      rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
    />
    <!-- CSS -->
    <link rel="stylesheet" href="./css/profileStyle.css" />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
      integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
  </head>
  <body>
    <div class="container-fluid">
      <div class="row mt-5">
        <div class="col-12 col-md-3 profile-image-container">
          <div class="profile-image mb-5">
            <img
              src="https://www.nj.com/resizer/zovGSasCaR41h_yUGYHXbVTQW2A=/1280x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/SJGKVE5UNVESVCW7BBOHKQCZVE.jpg"
              alt="profile-image"
              width="100%"
            />
          </div>
        </div>
        <div class="col-12 col-md-9">
          <ul class="profile-list row">
            <li class="profile-item col-12 col-md-6">
              <div class="profile-icon"><i class="fa-solid fa-user"></i></div>
              <div class="profile-body">
                <span class="profile-body__title">First Name </span>
                <p class="profile-body__content">Hoàng</p>
              </div>
            </li>
            <li class="profile-item col-12 col-md-6">
              <div class="profile-icon"><i class="fa-solid fa-user"></i></div>
              <div class="profile-body">
                <span class="profile-body__title">Last Name </span>
                <p class="profile-body__content">Trịnh Kim</p>
              </div>
            </li>
            <li class="profile-item col-12 col-md-6">
              <div class="profile-icon">
                <i class="fa-solid fa-mars-double"></i>
              </div>
              <div class="profile-body">
                <span class="profile-body__title">Giới tính </span>
                <p class="profile-body__content">Nam</p>
              </div>
            </li>
            <li class="profile-item col-12 col-md-6">
              <div class="profile-icon">
                <i class="fa-solid fa-calendar"></i>
              </div>
              <div class="profile-body">
                <span class="profile-body__title">Ngày sinh </span>
                <p class="profile-body__content">12/05/2001</p>
              </div>
            </li>
            <li class="profile-item col-12 col-md-6">
              <div class="profile-icon"><i class="fa-solid fa-phone"></i></div>
              <div class="profile-body">
                <span class="profile-body__title">Số điện thoại </span>
                <p class="profile-body__content">0937789787</p>
              </div>
            </li>
            <li class="profile-item col-12 col-md-6">
              <div class="profile-icon">
                <i class="fa-solid fa-id-card"></i>
              </div>
              <div class="profile-body">
                <span class="profile-body__title">Chứng minh nhân dân </span>
                <p class="profile-body__content">0793332254</p>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </body>
</html>
