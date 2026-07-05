import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {  
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
    if (message) {
      setMessage('');
    }
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
    if (message) {
      setMessage('');
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    if (!username.trim()) {
      setMessage('لطفاً نام کاربری را وارد کنید');
      return;
    }

    if (!password.trim()) {
      setMessage('لطفاً رمز عبور را وارد کنید');
      return;
    }

    if (password.length < 6) {
      setMessage('رمز عبور باید حداقل ۶ کاراکتر باشد');
      return;
    }

    setIsLoading(true);
    setMessage('در حال بررسی اطلاعات...');

    try {
      await new Promise(resolve => setTimeout(resolve, 1500));

      if (username === 'admin' && password === '123456') {
        setMessage('');
        navigate('/welcome');
      } else {
        setMessage('❌ نام کاربری یا رمز عبور اشتباه است');
      }
    } catch (error) {
      setMessage('❌ خطایی رخ داد. لطفاً دوباره تلاش کنید.');
      console.error('خطا در ورود:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-title">ورود به سیستم</h2>
        
        <form onSubmit={handleSubmit} className="login-form">
          <div className="login-input-group">
            <label htmlFor="username" className="login-label">
              نام کاربری:
            </label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={handleUsernameChange}
              placeholder="نام کاربری خود را وارد کنید"
              disabled={isLoading}
              required
              className="login-input"
            />
          </div>

          <div className="login-input-group">
            <label htmlFor="password" className="login-label">
              رمز عبور:
            </label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={handlePasswordChange}
              placeholder="رمز عبور خود را وارد کنید"
              disabled={isLoading}
              required
              className="login-input"
            />
          </div>

          <button
            type="submit"
            disabled={isLoading}
            className="login-button"
          >
            {isLoading ? 'در حال ورود...' : 'ورود'}
          </button>
        </form>

        {message && (
          <div
            className={`login-message ${message.includes('✅') ? 'login-success-message' : 'login-error-message'}`}
          >
            {message}
          </div>
        )}

        <div className="login-info-box">
          <p className="login-info-text">
            <strong>برای تست:</strong>
          </p>
          <p className="login-info-text">نام کاربری: admin</p>
          <p className="login-info-text">رمز عبور: 123456</p>
        </div>
      </div>
    </div>
  );
}

export default Login;

