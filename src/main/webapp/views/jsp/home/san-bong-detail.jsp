<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Styling -->
<link rel="stylesheet" href="./css/home/san-bong-detail.css" />

<div id="home-san-bong-detail"></div>
<div class="container mt-5">
  <div class="row mb-5">
    <div class="col-md-12 col-lg-8">
       <img
            src="../${sanBong.image}"
            width="100%"
            alt=""
        />
    </div>
    <div class="col-md-12 col-lg-4">
      <h3 class="title mb-3">${sanBong.name}</h3>
      <div class="rating d-flex mb-3">
        <ul class="rating-stars">
          <span class="rating-star">
            <i class="fa-solid fa-star"></i>
          </span>
          <span class="rating-star">
            <i class="fa-solid fa-star"></i>
          </span>
          <span class="rating-star">
            <i class="fa-solid fa-star"></i>
          </span>
          <span class="rating-star">
            <i class="fa-solid fa-star"></i>
          </span>
          <span class="rating-star">
            <i class="fa-regular fa-star"></i>
          </span>
        </ul>
        <span class="rating-num"><b>4/5</b></span>
        <span class="rating-quantity"><b>( 2 đánh giá )</b></span>
      </div>
      <div class="location mb-3">
        <i class="fa-solid fa-location-dot"></i>
        <span class="location-detail">
          ${sanBong.address}
        </span>
      </div>
      <div class="price mb-3">
        Giá:
        <span class="price-value" style="color: var(--main-color)"
          >${sanBong.cost}</span
        >
      </div>

      <button
        id="book-modal-btn"
        class="btn btn-primary"
        type="button"
        data-toggle="modal"
        data-target="#book-modal"
        style="width: 50%"
      >
        Đặt sân
      </button>
    </div>
  </div>
  <div class="row mb-5 g-0">
    <div class="col-md-12 col-lg-8 detail">
      <h2>Giới thiệu</h2>
      <div class="detail-content">
        ${sanBong.description}
      </div>
    </div>
  </div>
  <div class="row g-0">
    <div class="col-md-12 col-lg-8 google-map">
      <iframe
        src="${sanBong.map}"
        style="border: 0"
        allowfullscreen=""
        loading="lazy"
        referrerpolicy="no-referrer-when-downgrade"
      ></iframe>
    </div>
    <div class="col-md-12 col-lg-4 contact-wrapper">
      <h3>Thông tin liên hệ</h3>
      <p>Bạn có thể liên hệ với chủ sân bóng để biết thêm chi tiết về sân</p>
      <ul class="contact-list">
        <li class="contact-item">
          <div class="info-box">
            <span class="info-box__icon">
              <i class="fa-solid fa-user"></i>
            </span>
            <p class="info-box__text">Tên chủ sân: ${chuSan.lastName} ${chuSan.firstName}</p>
          </div>
        </li>
        <li class="contact-item">
          <div class="info-box">
            <span class="info-box__icon">
              <i class="fa-solid fa-location-dot"></i>
            </span>
            <p class="info-box__text">
              Địa chỉ: ${sanBong.address}
            </p>
          </div>
        </li>
        <li class="contact-item">
          <div class="info-box">
            <span class="info-box__icon">
              <i class="fa-solid fa-phone"></i>
            </span>
            <p class="info-box__text">${chuSan.sdt}</p>
          </div>
        </li>
        <li class="contact-item">
          <div class="info-box">
            <span class="info-box__icon">
              <i class="fa-solid fa-clock"></i>
            </span>
            <p class="info-box__text" id="textOpenTime">Giờ mở cửa: ${sanBong.openTime}</p>
          </div>
        </li>

        <li class="contact-item">
          <div class="info-box">
            <span class="info-box__icon">
              <i class="fa-solid fa-clock"></i>
            </span>
            <p class="info-box__text" id="textCloseTime">Giờ đóng cửa: ${sanBong.closeTime}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</div>

<!--Book MODAL -->
<div class="modal" tabindex="-1" role="dialog" id="book-modal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" style="color: var(--main-color)">
          Đặt sân bóng
        </h5>
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
        <form method="POST">
          <div class="form-group">
            <select id="selectBox" class="form-control mb-3">
              <option selected disabled="disabled">Chọn số sân</option>

              <c:forEach var = "i" begin = "1" end = "${sanBong.slot}">
                  <option value="${i}">Sân ${i}</option>
              </c:forEach>
            </select>
          </div>
          <div class="container-fluid">
            <ul class="time-list row">
                <!-- Data will be loaded by AJAX-->
            </ul>
          </div>

          <div class="form-group">
            <div
              id="fail-alert"
              class="alert alert-danger mt-2"
              style="opacity: 0; display: none"
            >
              This type of file is not allowed
            </div>
            <div
              id="success-alert"
              class="alert alert-success mt-2"
              style="opacity: 0; display: none"
            >
              This type of file is allowed
            </div>
          </div>
        </form>
      </div>

      <div class="modal-footer">
        <a>
          <button type="button" class="btn btn-primary" id="confirm-btn">
            Xác nhận
          </button>
        </a>

        <button type="button" class="btn btn-secondary" data-dismiss="modal">
          Close
        </button>
      </div>
    </div>
  </div>
</div>