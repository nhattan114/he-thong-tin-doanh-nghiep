<%@page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<div class="container vh-100 d-flex align-items-center justify-content-center">
  <div class="row w-100 shadow-lg bg-white rounded" style="padding: 90px 60px">
    <div class="col-12 col-lg-6">
      <object data="./svg/football2.svg" width="100%" height="100%"></object>
    </div>
    <div class="col-12 col-lg-6 mt-4">
      <form method="POST" action="?controller=login&action=login">
        <div class="form-group">
          <label for="username">Username:</label>
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text" id="icon-username">
                <i class="fa-solid fa-user"></i>
              </span>
            </div>
            <input
              type="text"
              class="form-control"
              name="username"
              id="username"
              placeholder="Username"
              aria-label="Username"
              aria-describedby="icon-username"
            />
          </div>
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
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
              placeholder="Password"
              aria-label="Username"
              aria-describedby="icon-password"
            />
          </div>
        </div>
        <!-- Show Message -->
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
        <div class="mb-2 float-left"><a href="/register">Sign up</a></div>
        <button
          id="login-button"
          type="button"
          class="btn btn-default w-100 p-2"
        >
          Submit
        </button>
      </form>
    </div>
  </div>
</div>
