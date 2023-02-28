package com.example.mapolymp

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carto.graphics.Color
import com.carto.styles.AnimationStyleBuilder
import com.carto.styles.AnimationType
import com.carto.styles.MarkerStyleBuilder
import com.carto.styles.TextStyleBuilder
import com.example.mapolymp.databinding.ActivityMainBinding
import org.neshan.common.model.LatLng
import org.neshan.mapsdk.internal.utils.BitmapUtils
import org.neshan.mapsdk.model.Label
import org.neshan.mapsdk.model.Marker

class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private var label: Label? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.apply {
            //زوم و حالت مپ
            map.setZoom(12f, 0f)
            //باز شدن مپ در محل مورد نظر
            map.moveCamera(LatLng(36.84939676631219, 54.4431511284253), 0f)
            map.setOnMapLongClickListener {
                //پر کردن مقدار لوکیشن
                addLabel(it)
            }
            map.setOnMapClickListener {
                map.addMarker(createMarker(it))
            }
            map.setOnMarkerDoubleClickListener {
                map.removeMarker(it)
            }
        }
    }

    //اضافه کردن لیبل جدید
    private fun addLabel(loc: LatLng) {
        //ساخت استایل برای لیبل
        val textStyleBuilder = TextStyleBuilder()
        textStyleBuilder.backgroundColor = Color(R.color.purple_200)
        val textStyle = textStyleBuilder.buildStyle()
        //پر کردن متغیر لیبل
        label = Label(loc, textStyle, "مکان انتخابی شما")
        //اختصاص دادن لیبل به مپ
        bind.map.addLabel(label)
    }

    //ساخت یک مارکر
    private fun createMarker(loc: LatLng): Marker {
        //ساخت یک انیمیشن
        val animationBuilder = AnimationStyleBuilder()
        animationBuilder.fadeAnimationType = AnimationType.ANIMATION_TYPE_SMOOTHSTEP
        animationBuilder.phaseInDuration = 0.5f
        animationBuilder.buildStyle()
        val animation = animationBuilder.buildStyle()

        //استایل برای مارکر
        val styleBuilder = MarkerStyleBuilder()
        styleBuilder.size = 15f
        styleBuilder.bitmap = BitmapUtils.createBitmapFromAndroidBitmap(
            BitmapFactory.decodeResource(resources, org.neshan.mapsdk.R.drawable.ic_marker)
        )
        styleBuilder.animationStyle = animation
        val style = styleBuilder.buildStyle()
        return Marker(loc, style)
    }
}