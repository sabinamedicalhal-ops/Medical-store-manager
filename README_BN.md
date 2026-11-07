# Medical Store Manager — Android Starter

এটি Android Studio-তে খোলার মতো একটি প্রাথমিক Kotlin + SQLite প্রজেক্ট।

## চালানোর নিয়ম
1. কম্পিউটারে Android Studio ইনস্টল করুন।
2. ZIP ফাইলটি Extract করুন।
3. Android Studio → Open → `MedicalStoreManager` ফোল্ডার নির্বাচন করুন।
4. Gradle Sync শেষ হতে দিন।
5. Android ফোন USB debugging চালু করে যুক্ত করুন, অথবা Emulator চালু করুন।
6. Run ▶ চাপুন।

## APK বানানো
Android Studio → Build → Build Bundle(s) / APK(s) → Build APK(s)।
সাধারণত APK পাবেন `app/build/outputs/apk/debug/app-debug.apk`-এ।

## এই স্টার্টার ভার্সনে আছে
- অফলাইন SQLite ডাটাবেস
- ওষুধ যোগ করা
- নাম, কোম্পানি ও ব্যাচ নম্বর দিয়ে সার্চ
- Quantity এবং expiry date সংরক্ষণ

## গুরুত্বপূর্ণ
এটি প্রথম প্রোটোটাইপ; পূর্ণ দোকান পরিচালনার জন্য billing, purchase, stock movement, expiry notifications,
backup/restore, user login, tax configuration এবং নির্ভুল আর্থিক রিপোর্ট যোগ ও পরীক্ষা করতে হবে।
Expiry তারিখ YYYY-MM-DD ফরম্যাটে লিখুন। বাস্তব দোকানে ব্যবহারের আগে ডেটা ব্যাকআপ ও হিসাব যাচাই করুন।
