<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="../../css/profileStyle.css"/>
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
            <p class="profile-body__content">${accDetail.firstName}</p>
          </div>
        </li>
        <li class="profile-item col-12 col-md-6">
          <div class="profile-icon"><i class="fa-solid fa-user"></i></div>
          <div class="profile-body">
            <span class="profile-body__title">Last Name </span>
            <p class="profile-body__content">${accDetail.lastName}</p>
          </div>
        </li>
        <li class="profile-item col-12 col-md-6">
          <div class="profile-icon">
            <i class="fa-solid fa-mars-double"></i>
          </div>
          <div class="profile-body">
            <span class="profile-body__title">Giới tính </span>
            <c:if test = "${accDetail.sex == false}">
                <p class="profile-body__content">Nữ</p>
            </c:if>
            <c:if test = "${accDetail.sex == true}">
                <p class="profile-body__content">Nam</p>
            </c:if>
          </div>
        </li>
        <li class="profile-item col-12 col-md-6">
          <div class="profile-icon">
            <i class="fa-solid fa-calendar"></i>
          </div>
          <div class="profile-body">
            <span class="profile-body__title">Ngày sinh </span>
            <p class="profile-body__content">${accDetail.birthDay}</p>
          </div>
        </li>
        <li class="profile-item col-12 col-md-6">
          <div class="profile-icon"><i class="fa-solid fa-phone"></i></div>
          <div class="profile-body">
            <span class="profile-body__title">Số điện thoại </span>
            <p class="profile-body__content">${accDetail.sdt}</p>
          </div>
        </li>
        <li class="profile-item col-12 col-md-6">
          <div class="profile-icon">
            <i class="fa-solid fa-id-card"></i>
          </div>
          <div class="profile-body">
            <span class="profile-body__title">Chứng minh nhân dân </span>
            <p class="profile-body__content">${accDetail.cmnd}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</div>
