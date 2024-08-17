package com.example.a4photo1word.presentation.info

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.a4photo1word.R

class InfoActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info)
        val textInfo = findViewById<TextView>(R.id.txt_info)
        textInfo.text =
            "   4 Pics 1 Word — 2013-yilda LOTUM GmbH tomonidan yaratilgan va iOS va Android uchun chiqarilgan so\'zli boshqotirma o\'yini. Pastki chap burchakda o\'yin ichidagi reklamalar uchun kalit ko\'rsatilgan skrinshot O\'yin 4 ta rasm 1 Word o\'yini juda oddiy: har bir darajada bitta so\'z bilan bog\'langan to\'rtta rasm ko\'rsatiladi; o\'yinchining maqsadi rasmlar ostida berilgan harflar to\'plamidan so\'z nima ekanligini aniqlashdir. O\'yinchilar ikki yoki uchta fotosurat o\'rtasidagi umumiylikni ko\'rishadi, lekin bog\'lovchi so\'zni aniqlay olmaydilar. Bu o\'yinda cheksiz tarkib va o\'yinchilarning qaytishini ta\'minlaydigan kundalik qiyinchiliklar mavjud. O\'yin freemium modeliga amal qilishi aytiladi : o\'yin bepul bo\'lsa-da, foydalanuvchining o\'yinda tezroq rivojlanishiga yordam beradigan mikrotransaktsiyalar mavjud. Aldashga ta\'siri O\'yin ingliz dialekti nuqtai nazaridan yozilgan, shuningdek, ba\'zi notanish so\'zlarni o\'z ichiga oladi, bu ba\'zi odamlarning to\'g\'ri javoblarni olish uchun aldash saytlari yoki ilovalariga murojaat qilishiga olib keladi. Psixologiya fanlari doktori Jeymi Madiganning so\'zlariga ko\'ra, \"odamlar anonim bo\'lganlarida aldash ehtimoli ko\'proq va ular boshqa o\'yinchilar bilan qanchalik ko\'p bog\'langan bo\'lsa, shunchalik ko\'p, boshqacha qilib aytganda, ular shunchalik ko\'p ma\'lum bo\'lsa, aldaydilar\". Qabul qilish 4 Pics 1 Word asosan ijobiy sharhlarni oldi. Kompyuter maslahatchisi Android versiyasi 4/5 ni berdi, uning ko'ngilochar qiymatini yuqori baholadi, lekin ijtimoiy jihatni yaxshilash mumkinligini aytdi. Pocket-lint uni 2013-yil 25-fevralda \"Kun ilovasi\" sifatida taqdim etib, uning soddaligi va ijtimoiy jihatini maqtagan. What Mobile ham unga 4/5 berdi, uning soddaligi va bajarilishini maqtab, \"maslahat\" tugmachasini ta\'kidlash chastotasini tanqid qildi. Bundan tashqari, o\'yinning nemischa versiyasi 4 Bilder 1 Wort va ispancha 4 Fotos 1 Palabra deb nomlangan."
        val wcc = WindowInsetsControllerCompat(window, window.decorView)
        wcc.isAppearanceLightStatusBars = false
    }

    fun back(view: View) {
        finish()
    }

}