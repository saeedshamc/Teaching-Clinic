// Import کردن کتابخانه‌های مورد نیاز از React
// useState: برای مدیریت state (وضعیت) کامپوننت
// useEffect: برای اجرای کد بعد از رندر شدن کامپوننت (مثل بارگذاری داده از API)
import React, { useState, useEffect } from 'react';
// useNavigate: برای هدایت کاربر به صفحات مختلف (Navigation)
import { useNavigate } from 'react-router-dom';

// تعریف کامپوننت Product
// این یک Functional Component است که صفحه نمایش محصولات را مدیریت می‌کند
function Product() {
  // ============================================
  // مدیریت State (وضعیت) کامپوننت
  // ============================================
  
  // State برای ذخیره لیست محصولات
  // مقدار اولیه: آرایه خالی []
  // products: لیست تمام محصولات
  // setProducts: تابع برای به‌روزرسانی لیست محصولات
  const [products, setProducts] = useState([]);
  
  // State برای مدیریت حالت ویرایش
  // مقدار اولیه: null (هیچ محصولی در حال ویرایش نیست)
  // editingProduct: محصولی که در حال ویرایش است (یا null اگر هیچ محصولی ویرایش نمی‌شود)
  // setEditingProduct: تابع برای شروع یا پایان ویرایش
  const [editingProduct, setEditingProduct] = useState(null);
  
  // State برای نمایش وضعیت بارگذاری
  // مقدار اولیه: true (در ابتدا در حال بارگذاری هستیم)
  // isLoading: آیا داده‌ها در حال بارگذاری هستند؟
  // setIsLoading: تابع برای تغییر وضعیت بارگذاری
  const [isLoading, setIsLoading] = useState(true);
  
  // استفاده از useNavigate برای هدایت کاربر
  // navigate: تابعی که می‌توانیم با آن کاربر را به صفحات دیگر ببریم
  const navigate = useNavigate();

  // ============================================
  // useEffect: بارگذاری محصولات از فایل JSON
  // ============================================
  // useEffect یک Hook است که بعد از رندر شدن کامپوننت اجرا می‌شود
  // آرایه وابستگی [] به این معنی است که فقط یک بار اجرا می‌شود (بعد از اولین رندر)
  useEffect(() => {
    // fetch: تابع برای دریافت داده از یک URL (API یا فایل)
    // '/products.json': مسیر فایل JSON در پوشه public
    fetch('/products.json')
      // .then(): وقتی درخواست موفقیت‌آمیز بود، این تابع اجرا می‌شود
      // response: پاسخ دریافتی از سرور
      .then(response => {
        // response.json(): تبدیل پاسخ به فرمت JSON
        // این یک Promise برمی‌گرداند
        return response.json();
      })
      // .then(): وقتی JSON با موفقیت تبدیل شد، این تابع اجرا می‌شود
      // data: داده‌های JSON که شامل یک object با کلید products است
      .then(data => {
        // data.products: آرایه محصولات از فایل JSON
        // setProducts: به‌روزرسانی state محصولات
        setProducts(data.products);
        // setIsLoading(false): تغییر وضعیت بارگذاری به false (بارگذاری تمام شد)
        setIsLoading(false);
      })
      // .catch(): اگر خطایی رخ داد، این تابع اجرا می‌شود
      // error: اطلاعات خطا
      .catch(error => {
        // console.error: نمایش خطا در کنسول مرورگر (برای دیباگ)
        console.error('خطا در بارگذاری محصولات:', error);
        // حتی در صورت خطا، وضعیت بارگذاری را false می‌کنیم
        setIsLoading(false);
      });
  }, []); // آرایه خالی [] یعنی فقط یک بار اجرا شود

  // ============================================
  // تابع افزایش تعداد محصول
  // ============================================
  // productId: شناسه محصولی که می‌خواهیم تعدادش را افزایش دهیم
  const increaseQuantity = (productId) => {
    // setProducts: به‌روزرسانی لیست محصولات
    // products.map(): ایجاد یک آرایه جدید با تغییرات
    // map روی هر محصول در آرایه products اجرا می‌شود
    setProducts(products.map(product =>
      // شرط: اگر id محصول با productId برابر باشد
      product.id === productId
        // ?: اگر شرط true باشد (محصول مورد نظر است)
        // ...product: کپی کردن تمام ویژگی‌های محصول فعلی
        // quantity: quantity + 1 (افزایش تعداد به اندازه 1)
        ? { ...product, quantity: product.quantity + 1 }
        // : اگر شرط false باشد (محصول مورد نظر نیست)
        // product: محصول را بدون تغییر برمی‌گردانیم
        : product
    ));
  };

  // ============================================
  // تابع کاهش تعداد محصول
  // ============================================
  // productId: شناسه محصولی که می‌خواهیم تعدادش را کاهش دهیم
  const decreaseQuantity = (productId) => {
    setProducts(products.map(product =>
      // شرط: اگر id محصول با productId برابر باشد AND تعداد بیشتر از 1 باشد
      // &&: عملگر AND (و)
      // product.quantity > 1: تعداد باید بیشتر از 1 باشد (نمی‌توانیم به 0 یا منفی برسیم)
      product.id === productId && product.quantity > 1
        // اگر شرط true باشد: کاهش تعداد به اندازه 1
        ? { ...product, quantity: product.quantity - 1 }
        // اگر شرط false باشد: محصول را بدون تغییر برمی‌گردانیم
        : product
    ));
  };

  // ============================================
  // تابع افزودن محصول به سبد خرید
  // ============================================
  // product: محصولی که می‌خواهیم به سبد خرید اضافه کنیم
  const addToCart = (product) => {
    // alert: نمایش یک پیام به کاربر
    // Template Literal (``): رشته‌ای که می‌توانیم متغیرها را در آن قرار دهیم
    // ${}: برای قرار دادن مقدار متغیر در رشته
    // product.name: نام محصول
    // product.quantity: تعداد انتخاب شده
    // product.price * product.quantity: قیمت کل (قیمت واحد × تعداد)
    // .toLocaleString('fa-IR'): فرمت کردن عدد به فرمت فارسی (مثلاً 25,000,000)
    alert(`✅ محصول "${product.name}" با تعداد ${product.quantity} به سبد خرید اضافه شد!\nقیمت کل: ${(product.price * product.quantity).toLocaleString('fa-IR')} تومان`);
    // در اینجا می‌توانید منطق سبد خرید را اضافه کنید
    // مثلاً: ذخیره در localStorage، ارسال به سرور، و غیره
  };

  // ============================================
  // تابع شروع ویرایش محصول
  // ============================================
  // product: محصولی که می‌خواهیم ویرایش کنیم
  const startEdit = (product) => {
    // setEditingProduct: شروع حالت ویرایش
    // { ...product }: کپی کردن تمام ویژگی‌های محصول
    // این کار را می‌کنیم تا اگر کاربر لغو کند، داده اصلی تغییر نکند
    setEditingProduct({ ...product });
  };

  // ============================================
  // تابع ذخیره تغییرات ویرایش
  // ============================================
  const saveEdit = () => {
    // اعتبارسنجی: بررسی اینکه نام خالی نباشد و قیمت معتبر باشد
    // !editingProduct.name.trim(): اگر نام خالی باشد یا فقط فاصله داشته باشد
    // trim(): حذف فاصله‌های اضافی از ابتدا و انتهای رشته
    // editingProduct.price <= 0: اگر قیمت کمتر یا مساوی صفر باشد
    if (!editingProduct.name.trim() || editingProduct.price <= 0) {
      // نمایش پیام خطا و توقف اجرای تابع
      alert('لطفاً نام و قیمت معتبر وارد کنید');
      return; // توقف اجرای تابع
    }

    // به‌روزرسانی لیست محصولات
    setProducts(products.map(product =>
      // اگر id محصول با id محصول در حال ویرایش برابر باشد
      product.id === editingProduct.id
        // ?: محصول ویرایش شده را جایگزین کن
        ? editingProduct
        // : در غیر این صورت، محصول را بدون تغییر نگه دار
        : product
    ));
    
    // setEditingProduct(null): پایان حالت ویرایش
    // null یعنی هیچ محصولی در حال ویرایش نیست
    setEditingProduct(null);
    
    // نمایش پیام موفقیت
    alert('✅ تغییرات با موفقیت ذخیره شد');
  };

  // ============================================
  // تابع لغو ویرایش
  // ============================================
  const cancelEdit = () => {
    // setEditingProduct(null): لغو حالت ویرایش
    // با این کار، تغییرات اعمال نمی‌شود و به حالت نمایش برمی‌گردیم
    setEditingProduct(null);
  };

  // ============================================
  // تابع مدیریت تغییرات فیلدهای ویرایش
  // ============================================
  // field: نام فیلدی که تغییر کرده (مثلاً 'name', 'price', 'description')
  // value: مقدار جدیدی که کاربر وارد کرده
  const handleEditChange = (field, value) => {
    // setEditingProduct: به‌روزرسانی محصول در حال ویرایش
    setEditingProduct({
      // ...editingProduct: کپی کردن تمام ویژگی‌های قبلی
      ...editingProduct,
      // [field]: استفاده از bracket notation برای تغییر فیلد مورد نظر
      // field === 'price' ? parseFloat(value) || 0 : value
      // اگر فیلد 'price' باشد: تبدیل به عدد (parseFloat) و اگر تبدیل نشد، 0 قرار بده
      // در غیر این صورت: مقدار را همان‌طور که هست نگه دار
      [field]: field === 'price' ? parseFloat(value) || 0 : value
    });
  };

  // ============================================
  // تابع خروج از حساب کاربری
  // ============================================
  const handleLogout = () => {
    // window.confirm: نمایش یک پنجره تایید به کاربر
    // اگر کاربر OK را بزند، true برمی‌گرداند
    if (window.confirm('آیا می‌خواهید خارج شوید؟')) {
      // navigate('/'): هدایت کاربر به صفحه اصلی (صفحه ورود)
      navigate('/');
    }
    // اگر کاربر Cancel را بزند، هیچ کاری انجام نمی‌شود
  };

  // ============================================
  // نمایش صفحه بارگذاری
  // ============================================
  // اگر isLoading برابر true باشد، این بخش نمایش داده می‌شود
  if (isLoading) {
    return (
      // return: بازگرداندن JSX (کد HTML)
      <div className="product-loading-container">
        <div className="product-loading-text">در حال بارگذاری...</div>
      </div>
    );
  }

  // ============================================
  // بازگرداندن JSX اصلی (صفحه محصولات)
  // ============================================
  return (
    // div اصلی که کل صفحه را در بر می‌گیرد
    <div className="product-container">
      {/* هدر صفحه (عنوان و دکمه خروج) */}
      <div className="product-header">
        {/* عنوان صفحه */}
        <h1 className="product-title">فروشگاه محصولات</h1>
        
        {/* دکمه خروج */}
        {/* onClick: وقتی کاربر روی دکمه کلیک کند، تابع handleLogout اجرا می‌شود */}
        <button onClick={handleLogout} className="product-logout-button">
          خروج
        </button>
      </div>

      {/* شبکه نمایش محصولات (Grid Layout) */}
      <div className="product-products-grid">
        {/* products.map(): تبدیل آرایه محصولات به المان‌های JSX */}
        {/* product: هر محصول در آرایه */}
        {/* =>: arrow function (تابع پیکانی) */}
        {products.map(product => (
          // div برای هر کارت محصول
          // key: یک شناسه یکتا برای React (برای بهینه‌سازی رندر)
          // product.id: استفاده از id محصول به عنوان key
          <div key={product.id} className="product-product-card">
            {/* شرط: اگر محصول در حال ویرایش باشد */}
            {/* editingProduct && editingProduct.id === product.id */}
            {/* &&: اگر editingProduct وجود داشته باشد (null نباشد) AND id آن با id محصول برابر باشد */}
            {editingProduct && editingProduct.id === product.id ? (
              // ============================================
              // حالت ویرایش: نمایش فرم ویرایش
              // ============================================
              <div className="product-edit-form">
                {/* عنوان فرم ویرایش */}
                <h3 className="product-edit-title">ویرایش محصول</h3>
                
                {/* فیلد ویرایش نام محصول */}
                <div className="product-edit-field">
                  {/* label: برچسب فیلد */}
                  <label className="product-edit-label">نام محصول:</label>
                  {/* input: فیلد ورودی متن */}
                  <input
                    type="text" // نوع input: متن
                    value={editingProduct.name} // مقدار فعلی از state
                    // onChange: وقتی کاربر چیزی تایپ کند، این تابع اجرا می‌شود
                    // e.target.value: مقدار جدیدی که کاربر وارد کرده
                    // handleEditChange('name', ...): تغییر فیلد 'name'
                    onChange={(e) => handleEditChange('name', e.target.value)}
                    className="product-edit-input"
                  />
                </div>

                {/* فیلد ویرایش قیمت */}
                <div className="product-edit-field">
                  <label className="product-edit-label">قیمت (تومان):</label>
                  {/* input: فیلد ورودی عدد */}
                  <input
                    type="number" // نوع input: عدد
                    value={editingProduct.price} // مقدار فعلی از state
                    // onChange: تغییر فیلد 'price'
                    onChange={(e) => handleEditChange('price', e.target.value)}
                    className="product-edit-input"
                    min="0" // حداقل مقدار: 0 (قیمت نمی‌تواند منفی باشد)
                  />
                </div>

                {/* فیلد ویرایش توضیحات */}
                <div className="product-edit-field">
                  <label className="product-edit-label">توضیحات:</label>
                  {/* textarea: فیلد ورودی چندخطی */}
                  <textarea
                    value={editingProduct.description} // مقدار فعلی از state
                    // onChange: تغییر فیلد 'description'
                    onChange={(e) => handleEditChange('description', e.target.value)}
                    className="product-edit-textarea"
                    rows="4" // تعداد سطرهای قابل مشاهده
                  />
                </div>

                {/* دکمه‌های ذخیره و لغو */}
                <div className="product-edit-buttons">
                  {/* دکمه ذخیره */}
                  {/* onClick: اجرای تابع saveEdit */}
                  <button onClick={saveEdit} className="product-save-button">
                    ذخیره
                  </button>
                  {/* دکمه لغو */}
                  {/* onClick: اجرای تابع cancelEdit */}
                  <button onClick={cancelEdit} className="product-cancel-button">
                    لغو
                  </button>
                </div>
              </div>
            ) : (
              // ============================================
              // حالت نمایش: نمایش اطلاعات محصول
              // ============================================
              // <>: Fragment (یک wrapper نامرئی برای گروه‌بندی المان‌ها)
              <>
                {/* بخش تصویر محصول */}
                <div className="product-product-image">
                  {/* img: تگ تصویر */}
                  <img 
                    src={product.image} // مسیر تصویر محصول
                    alt={product.name} // متن جایگزین (برای دسترسی‌پذیری)
                    className="product-image"
                    // onError: اگر تصویر بارگذاری نشد، این تابع اجرا می‌شود
                    // e.target.style.display = 'none': مخفی کردن تصویر در صورت خطا
                    onError={(e) => {
                      e.target.style.display = 'none';
                    }}
                  />
                </div>

                {/* بخش اطلاعات محصول */}
                <div className="product-product-info">
                  {/* نام محصول */}
                  <h2 className="product-product-name">{product.name}</h2>
                  
                  {/* بخش قیمت */}
                  <div className="product-price-section">
                    {/* برچسب قیمت */}
                    <span className="product-price-label">قیمت:</span>
                    {/* نمایش قیمت با فرمت فارسی */}
                    <span className="product-price">
                      {/* .toLocaleString('fa-IR'): فرمت کردن عدد به فارسی */}
                      {product.price.toLocaleString('fa-IR')} تومان
                    </span>
                  </div>

                  {/* بخش توضیحات */}
                  <div className="product-description-section">
                    {/* عنوان بخش توضیحات */}
                    <h3 className="product-description-title">توضیحات:</h3>
                    {/* متن توضیحات */}
                    <p className="product-description">{product.description}</p>
                  </div>

                  {/* بخش کنترل تعداد */}
                  <div className="product-quantity-section">
                    {/* برچسب تعداد */}
                    <span className="product-quantity-label">تعداد:</span>
                    {/* کنترل‌های افزایش و کاهش */}
                    <div className="product-quantity-controls">
                      {/* دکمه کاهش */}
                      <button
                        // onClick: کاهش تعداد محصول
                        // () => decreaseQuantity(product.id): arrow function برای فراخوانی تابع
                        onClick={() => decreaseQuantity(product.id)}
                        className="product-quantity-button"
                        // disabled: غیرفعال کردن دکمه اگر تعداد 1 یا کمتر باشد
                        disabled={product.quantity <= 1}
                      >
                        − {/* نماد منفی */}
                      </button>
                      {/* نمایش تعداد فعلی */}
                      <span className="product-quantity-value">{product.quantity}</span>
                      {/* دکمه افزایش */}
                      <button
                        // onClick: افزایش تعداد محصول
                        onClick={() => increaseQuantity(product.id)}
                        className="product-quantity-button"
                      >
                        + {/* نماد مثبت */}
                      </button>
                    </div>
                  </div>

                  {/* بخش قیمت کل */}
                  <div className="product-total-price">
                    <span>قیمت کل: </span>
                    {/* نمایش قیمت کل (قیمت واحد × تعداد) */}
                    <span className="product-total-price-value">
                      {/* (product.price * product.quantity): محاسبه قیمت کل */}
                      {(product.price * product.quantity).toLocaleString('fa-IR')} تومان
                    </span>
                  </div>

                  {/* دکمه‌های عملیات */}
                  <div className="product-action-buttons">
                    {/* دکمه افزودن به سبد خرید */}
                    <button
                      // onClick: افزودن محصول به سبد خرید
                      onClick={() => addToCart(product)}
                      className="product-add-to-cart-button"
                    >
                      🛒 افزودن به سبد خرید
                    </button>
                    {/* دکمه ویرایش */}
                    <button
                      // onClick: شروع ویرایش محصول
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

// Export کردن کامپوننت برای استفاده در فایل‌های دیگر
export default Product;
