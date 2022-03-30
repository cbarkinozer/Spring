# Bitirme Projesi

Projenin Konusu:
Bir marketteki ürünlerin satış fiyatlarına göre son fiyatlarını belirleyen servisin Spring Boot Framework
kullanılarak yazılması ve isteğe bağlı olarak önyüzünün yazılması.

> **Gereksinimler:**

> **Backend:**

- Kullanıcıdan kullanıcı adı, şifre, isim, soy isim bilgilerini alarak sisteme kayıt yapılır.  ✓
- Sisteme kayıtlı kullanıcılar market ürünlerinin veri girişini yapabilir. (security) ✓
- Ürün türlerine göre KDV oranları değişiklik göstermektedir. Bu oranlar aşağıdaki tabloda (vatRate) ✓
belirtilmiştir.   
- __**Zaman zaman KDV oranları değişiklik gösterebilmektedir.**__ ✓

![Image](https://www.linkpicture.com/q/Untitled_395.png)


- Ürün için veri girişi yapacak kullanıcı; ürünün adı, ürünün türü ve vergisiz satış fiyatı alanlarını
doldurur. Her bir ürün için KDV Tutarı ve ürünün son fiyatı da hesaplanarak sisteme kaydedilir. (calculatePrice) ✓
> **Kurallar ve gereksinimler:**
- Sisteme yeni kullanıcı tanımlanabilir, güncellenebilir ve silinebilir. (saveUser, updateUser, deleteUser) ✓
- Sisteme yeni ürünler tanımlanabilir, güncellenebilir ve silinebilir. (saveProduct, updateProduct, deleteProduct) ✓
- Ürünlerin fiyatları güncellenebilir. (updatePrice) ✓
- KDV oranları değiştiğinde sistemdeki ürünlere de bu değişiklik yansıtılmalıdır. (batchUpdateProducts when updateVatRate) ✓
- Herhangi bir hata durumunda tüm işlemler geri alınmalıdır. (transactional) ✓
- Tüm ürünler listelenebilmelidir. (findAllProducts) ✓
- Belirli bir fiyat aralığındaki ürünler listelenebilmelidir. (findProductsByPriceInterval) ✓
- Ürün türlerine göre ürünler listelenebilmelidir. (findProductsByProductType) ✓
- Ürün türlerine göre aşağıdaki gibi detay veri içeren bir bilgilendirme alınabilmelidir. (productAnalysis) ✓

![Image](https://www.linkpicture.com/q/22_57.png)

> Validasyonlar: 
- Aynı kullanıcı adı ile kullanıcı tanımı yapılamaz. ✓
- Kullanıcı girişi kullanıcı adı & şifre kombinasyonu ile yapılır. (security) ✓
- Ürün türü, fiyatı, adı gibi alanlar boş olamaz. ✓
- Ürün fiyatı sıfır ya da negatif olamaz. ✓
- KDV oranı negatif olamaz. ✓
> Authentication:
- Güvenli endpointler kullanınız. (jwt, bearer vs. ) (security) ✓
> Response:
- Başarılı ve başarısız responselar için modeller tanımlayın ve bunları kullanın. ✓
> Dökümantasyon:
- Open API Specification (Swagger tercih sebebi) ✓
> Exception Handling:
- Hatalı işlemler için doğru hata kodlarının dönüldüğünden emin olunuz. ✓
> Test:
- Unit ve integration testleri yazınız. ✓

### Explanation of the Design Decisions

All requirements are satisfied.  
Time spent on the project : 80+ hours.   
Line of code count : 4700  
Explanation and demonstration video : https://youtu.be/4ZQT0eh_H6k  
Entity and controller design: https://ibb.co/f0DBp1T  

- VatRate is stored in a table because I want API users to be able to specify desired VAT rates for specific products
without changing the source code.  
- I added soft delete (cancel) to the Users table because users frequently return to apps after deleting their accounts,
and user data may be useful for data science (in the future).  
- VAT table has hard delete because it is very small and its data is unimportant, and
Product table has hard delete because I expected a high product count (multiple products per user).  
- I wanted to make queries and code simpler, faster,and I also wanted to reduce storage and query time costs.  
- There are findAll and findById endpoints to the controllers as extra, because I needed them while using the API.
- I added integration tests for controllers (to test the flow and API path) and unit tests for service layers by mocking.  
- Happy case, empty case, validations, and null parameters tests are written.   
- Some tests are not working properly.
