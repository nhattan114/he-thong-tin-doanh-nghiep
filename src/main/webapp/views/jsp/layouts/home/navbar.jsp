<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar" style="background: linear-gradient(to right, #8e54e9, #4776e6)">
    <section class="navbar-top">
      <span class="logo">
        <p class="logo__title">Soccer Booking</p>
      </span>
    </section>

    <section class="navbar-middle">
      <ul class="navbar__list">
        <li class="navbar__item">
          <a href="/" class="navbar__item-link">Trang chủ</a>
        </li>
        <li class="navbar__item">
          <a href="/don-dat-san" class="navbar__item-link">Lịch sử đặt sân</a>
        </li>
      </ul>
    </section>

      <section class="navbar-bottom">
        <c:if test = "${isLogin == false}">
          <a href="/login">
            <i class="fa-solid fa-user"></i>
            Đăng nhập
          </a>
        </c:if>
          
          <c:if test = "${isLogin == true}">
          <div class="login-menu">
            <i class="fa-solid fa-gear" id="login-menu-btn"></i>
            <div class="drop-down">
              <ul class="drop-down-list">
                <li class="drop-down-item">
                  <a href="/profile"
                    ><i class="fa-solid fa-address-card"></i> Profile</a
                  >
                </li>
                 <li class="drop-down-item">
                  <a href="/change-password">
                    <i class="fa-solid fa-lock"></i> Đổi mật khẩu</a
                  >
                </li>
                <li class="drop-down-item">
                  <a href="/logout">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i> Đăng
                    xuất</a
                  >
                </li>
              </ul>
            </div>
          </div>
        </c:if>

        </section>

    <section class="navbar-menu">
      <button id="menu-btn">
        <i class="fa-solid fa-bars"></i>
      </button>
    </section>
  </nav>