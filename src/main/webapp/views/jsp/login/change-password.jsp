<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div class="container vh-100 d-flex align-items-center justify-content-center">
  <div class="row w-100 shadow-lg bg-white rounded" style="padding: 90px 60px">
    <div class="col-12 col-lg-6">
      <object
        data="./svg/change-password.svg"
        width="100%"
        height="100%"
      ></object>
    </div>
    <div class="col-12 col-lg-6 mt-4">
      <form method="POST" action="?controller=login&action=login">
        <div class="form-group">
          <label for="currentPassword">Current password:</label>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="icon-currentPassword">
                <i class="fa-solid fa-lock"></i>
              </span>
            </div>
            <input
              type="password"
              class="form-control"
              name="currentPassword"
              id="currentPassword"
              placeholder="Your current password"
              aria-label="Current password"
              aria-describedby="icon-currentPassword"
            />
          </div>
        </div>
        <div class="form-group">
          <label for="newPassword">New password:</label>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="icon-password">
                <i class="fa-solid fa-lock"></i>
              </span>
            </div>
            <input
              type="password"
              class="form-control"
              name="password"
              id="password"
              placeholder="Your new password"
              aria-label="Username"
              aria-describedby="icon-password"
            />
          </div>
        </div>
        <div class="form-group">
          <label for="confirmPassword">Confirm password:</label>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="icon-password">
                <i class="fa-solid fa-lock"></i>
              </span>
            </div>
            <input
              type="password"
              class="form-control"
              name="confirmPassword"
              id="confirmPassword"
              placeholder="Please confirm your new password"
              aria-label="Confirm Password"
              aria-describedby="icon-confirmPassword"
            />
          </div>
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
        <button
          id="change-button"
          type="button"
          class="btn btn-default w-100 p-2 mt-3"
        >
          Change password
        </button>
      </form>
    </div>
  </div>
</div>
