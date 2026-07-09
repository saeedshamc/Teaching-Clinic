 import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function Product() {
  const [products, setProducts] = useState([]);
  const [editingProduct, setEditingProduct] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    fetch('/products.json')
      .then(response => {
        return response.json();
      })
      .then(data => {
        setProducts(data.products);
        setIsLoading(false);
      })
      .catch(error => {
        console.error('خطا در بارگذاری محصولات:', error);
        setIsLoading(false);
      });
  }, []);

  const increaseQuantity = (productId) => {
    setProducts(products.map(product =>
      product.id === productId
        ? { ...product, quantity: product.quantity + 1 }
        : product
    ));
  };

  const decreaseQuantity = (productId) => {
    setProducts(products.map(product =>
      product.id === productId && product.quantity > 1
        ? { ...product, quantity: product.quantity - 1 }
        : product
    ));
  };

  const addToCart = (product) => {
    alert(`✅ محصول "${product.name}" با تعداد ${product.quantity} به سبد خرید اضافه شد!\nقیمت کل: ${(product.price * product.quantity).toLocaleString('fa-IR')} تومان`);
  };

  const startEdit = (product) => {
    setEditingProduct({ ...product });
  };

  const saveEdit = () => {
    if (!editingProduct.name.trim() || editingProduct.price <= 0) {
      alert('لطفاً نام و قیمت معتبر وارد کنید');
      return;
    }

    setProducts(products.map(product =>
      product.id === editingProduct.id
        ? editingProduct
        : product
    ));
    
    setEditingProduct(null);
    alert('✅ تغییرات با موفقیت ذخیره شد');
  };

  const cancelEdit = () => {
    setEditingProduct(null);
  };

  const handleEditChange = (field, value) => {
    setEditingProduct({
      ...editingProduct,
      [field]: field === 'price' ? parseFloat(value) || 0 : value
    });
  };

  const handleLogout = () => {
    if (window.confirm('آیا می‌خواهید خارج شوید؟')) {
      navigate('/');
    }
  };

  if (isLoading) {
    return (
      <div className="product-loading-container">
        <div className="product-loading-text">در حال بارگذاری...</div>
      </div>
    );
  }

  return (
    <div className="product-container">
      <div className="product-header">
        <h1 className="product-title">فروشگاه محصولات</h1>
        <button onClick={handleLogout} className="product-logout-button">
          خروج
        </button>
      </div>

      <div className="product-products-grid">
        {products.map(product => (
          <div key={product.id} className="product-product-card">
            {editingProduct && editingProduct.id === product.id ? (
              <div className="product-edit-form">
                <h3 className="product-edit-title">ویرایش محصول</h3>
                
                <div className="product-edit-field">
                  <label className="product-edit-label">نام محصول:</label>
                  <input
                    type="text"
                    value={editingProduct.name}
                    onChange={(e) => handleEditChange('name', e.target.value)}
                    className="product-edit-input"
                  />
                </div>

                <div className="product-edit-field">
                  <label className="product-edit-label">قیمت (تومان):</label>
                  <input
                    type="number"
                    value={editingProduct.price}
                    onChange={(e) => handleEditChange('price', e.target.value)}
                    className="product-edit-input"
                    min="0"
                  />
                </div>

                <div className="product-edit-field">
                  <label className="product-edit-label">توضیحات:</label>
                  <textarea
                    value={editingProduct.description}
                    onChange={(e) => handleEditChange('description', e.target.value)}
                    className="product-edit-textarea"
                    rows="4"
                  />
                </div>

                <div className="product-edit-buttons">
                  <button onClick={saveEdit} className="product-save-button">
                    ذخیره
                  </button>
                  <button onClick={cancelEdit} className="product-cancel-button">
                    لغو
                  </button>
                </div>
              </div>
            ) : (
              <>
                <div className="product-product-image">
                  <img 
                    src={product.image}
                    alt={product.name}
                    className="product-image"
                    onError={(e) => {
                      e.target.style.display = 'none';
                    }}
                  />
                </div>

                <div className="product-product-info">
                  <h2 className="product-product-name">{product.name}</h2>
                  
                  <div className="product-price-section">
                    <span className="product-price-label">قیمت:</span>
                    <span className="product-price">
                      {product.price.toLocaleString('fa-IR')} تومان
                    </span>
                  </div>

                  <div className="product-description-section">
                    <h3 className="product-description-title">توضیحات:</h3>
                    <p className="product-description">{product.description}</p>
                  </div>

                  <div className="product-quantity-section">
                    <span className="product-quantity-label">تعداد:</span>
                    <div className="product-quantity-controls">
                      <button
                        onClick={() => decreaseQuantity(product.id)}
                        className="product-quantity-button"
                        disabled={product.quantity <= 1}
                      >
                        −
                      </button>
                      <span className="product-quantity-value">{product.quantity}</span>
                      <button
                        onClick={() => increaseQuantity(product.id)}
                        className="product-quantity-button"
                      >
                        +
                      </button>
                    </div>
                  </div>

                  <div className="product-total-price">
                    <span>قیمت کل: </span>
                    <span className="product-total-price-value">
                      {(product.price * product.quantity).toLocaleString('fa-IR')} تومان
                    </span>
                  </div>

                  <div className="product-action-buttons">
                    <button
                      onClick={() => addToCart(product)}
                      className="product-add-to-cart-button"
                    >
                      🛒 افزودن به سبد خرید
                    </button>
                    <button
                      onClick={() => startEdit(product)}
                      className="product-edit-button"
                    >
                      ✏️ ویرایش
                    </button>
                  </div>
                </div>
              </>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

export default Product;

