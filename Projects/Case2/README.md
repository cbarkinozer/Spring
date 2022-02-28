# Case2

## Adres Kayıt Sistemi:  
Bir adreste bulunması gereken alanlar:  
- Ülke 
-  Şehir 
-  İlçe 
-  Mahalle 
-  Sokak 
-  Kapı No  
-  Daire No 
Bir adet controller yazınız(Tek controller yeterli). Bu controller içerisinde aşağıdaki işlemler yapılabilmelidir.  
1. Ülke kaydedilebilmelidir. ✓
2. Ülke kodundan ülke sorgulanabilmelidir. ✓
3. Şehir kaydedilebilmelidir. ✓
4. Plakadan şehir bilgisi sorgulanabilmelidir. ✓
5. İlçe  kaydedilebilmelidir. ✓
6. Bir ile ait ilçeler sorgulanabilmelidir. ✓
7. Mahalle kaydedilebilmelidir. ✓
8. Mahalle adını güncellenebilmelidir.  ✓
9. Bir ilçeye ait mahalleler sorgulanabilmelidir. ✓
10. Sokak kaydedilebilmelidir. ✓
11. Sokak adı güncellenebilmelidir. ✓
12. Bir mahalleye ait sokaklar sorgulanabilmelidir.
13. Adres kaydedilebilmelidir.  ✓
14. Adres silinebilmelidir. ✓
15. Id den adres bilgisi edinilebilmelidir. ✓
 
### NOT:  
- Address entitysi hariç diğer entitylerin servislerinde entity kullanabilirsiniz.
Yani metot parametresi için dto, return için başka bir dto kullanmanıza gerek yok.  
- Address kaydeden dönen vs yerlerde mapper ya da converterlar kullanarak DTO ile veri akışını sağlayınız. ✓

### Design:  
Table Design Image:  https://ibb.co/Xs8S5Wc    
Note: plateCode renamed as cityCode.   
Doors does not have name, they only have doorId.      
In City, city Code is cities unique code. E.g 01 for Adana, 872 for chicago, 011 for delhi.  
Country has country code that is a unique abbreviation for country. For example: "TR" for Turkey.    

### TODO:  
Fix remaining errors    
Test on Swagger  

## Remaining errors:   
'Many To One' attribute type should not be '****' 24,26,28,30
