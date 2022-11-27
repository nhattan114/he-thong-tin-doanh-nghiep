<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="trang-chu-home"></div>
<main class="body">
  <div class="container">
    <div class="row">
      <div class="col-12">
        <div class="product-search">
          <div class="search-box">
            <form action="">
              <input
                type="text"
                class="search-box__input"
                id ="search-by-name"
                placeholder="Tìm kiếm sân theo tên"
              />
              <button type="button" class="search-box__btn">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </form>
          </div>
          <button class="product-search__btn">Tìm sân</button>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <h1>Sân bóng đá Hồ Chí Minh</h1>
      </div>
    </div>
    <ul class="row products">
       <!-- Content will be loaded by ajax -->
    </ul>
  </div>
</main>
