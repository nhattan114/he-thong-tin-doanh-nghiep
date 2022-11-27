<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="slider">
    <ul class="slider-dots">
      <li class="slider-dots__item" data-index="0"></li>
      <li class="slider-dots__item" data-index="1"></li>
      <li class="slider-dots__item" data-index="2"></li>
    </ul>
    <div class="slider-arrow slider-arrow--left">
      <i class="fa-solid fa-arrow-left"></i>
    </div>
    <div class="slider-arrow slider-arrow--right">
      <i class="fa-solid fa-arrow-right"></i>
    </div>
    <div class="slider-wrapper">
      <div class="slider-main">
        <div class="slider-main__item">
          <img src="./images/slide1.jpg" class="slider__img" alt="" />
        </div>
        <div class="slider-main__item">
          <img src="./images/slide2.jpg" class="slider__img" alt="" />
        </div>
        <div class="slider-main__item">
          <img src="./images/slide3.jpg" class="slider__img" alt="" />
        </div>
      </div>
    </div>
  </div>
