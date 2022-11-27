<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="sidebar">
    <div class="avatar">
      <img
        src="https://www.nj.com/resizer/zovGSasCaR41h_yUGYHXbVTQW2A=/1280x0/smart/cloudfront-us-east-1.images.arcpublishing.com/advancelocal/SJGKVE5UNVESVCW7BBOHKQCZVE.jpg"
        alt="Avatar"
        class="avatar-img"
      />
      <h4 class="avatar-name">Johnny Pfizer</h4>
    </div>
    <div class="menu">
      <i class="bx bx-menu d-none d-sm-inline-block" id="menu-btn"></i>
    </div>
   <ul class="nav-list">
       <c:if test = "${role == 1}">
        <li>
          <a
            href="/profile"
            data-toggle="tooltip"
            data-placement="right"
            title="Profile"
          >
            <i class="bx bxs-user-circle"></i>
            <span class="links-name">Profile</span>
          </a>
        </li>
        <li>
          <a
            href="/chusan/quan-ly-dat-san"
            data-toggle="tooltip"
            data-placement="right"
            title="Quản lý đặt sân"
          >
            <i class="bx bx-task"></i>
            <span class="links-name">Quản lý đặt sân</span>
          </a>
        </li>
        <li>
          <a
            href="/chusan"
            data-toggle="tooltip"
            data-placement="right"
            title="Quản lý sân bóng"
          >
            <i class="bx bx-run"></i>
            <span class="links-name">Quản lý sân bóng</span>
          </a>
        </li>
        <li>
          <a
            href="/chusan/lich-su-don-dat-san"
            data-toggle="tooltip"
            data-placement="right"
            title="Lịch sử đơn đặt sân"
          >
            <i class="bx bx-history"></i>
            <span class="links-name">Lịch sử đơn đặt sân</span>
          </a>
        </li>
        </c:if>
        <c:if test = "${role == 2}">
            <li>
          <a
            href="/admin"
            data-toggle="tooltip"
            data-placement="right"
            title="Quản lý tài khoản"
          >
            <i class="fa-solid fa-users"></i>
            <span class="links-name">Quản lý tài khoản</span>
          </a>
        </li>
        </c:if>  
        <li id="res-log-out" class="d-block d-sm-none">
          <a href="#" data-toggle="tooltip" data-placement="right" title="">
            <span class="links-name"
              ><a href="/logout"
                ><i class="bx bx-log-out"></i> Log out</a
              ></span
            >
          </a>
        </li>
      </ul>
</div>