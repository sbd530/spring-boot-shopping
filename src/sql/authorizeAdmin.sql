-- email 을 admin@admin.com 으로 회원가입후 쿼리 실행
UPDATE user
SET role = 'ADMIN'
WHERE email = 'admin@admin.com';