package com.example.selectimagegallery

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.selectimagegallery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val imageList = listOf(
        R.drawable.sample1,
        R.drawable.sample2,
        R.drawable.sample3,
        R.drawable.sample4,
        R.drawable.sample5,
        R.drawable.sample6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // GridView에 어댑터를 설정합니다.
        binding.gridView.adapter = ImageAdapter(this, imageList)

        // GridView의 아이템 클릭 리스너: 선택한 이미지 리소스를 largeImageView에 적용하고 보이게 합니다.
        binding.gridView.setOnItemClickListener { _, _, position, _ ->
            val selectedImage = imageList[position]
            // also를 사용하여 ImageView에 이미지 리소스를 적용하고 visibility를 변경합니다.
            binding.largeImageView.also {
                it.setImageResource(selectedImage)
                it.visibility = View.VISIBLE
            }
        }
    }

    // GridView에 이미지를 표시하기 위한 어댑터 클래스
    private class ImageAdapter(val context: Context, val images: List<Int>) : BaseAdapter() {
        override fun getCount(): Int = images.size
        override fun getItem(position: Int): Any = images[position]
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val imageView = (convertView as? ImageView) ?: ImageView(context).apply {
                layoutParams = AbsListView.LayoutParams(200, 200)
                scaleType = ImageView.ScaleType.CENTER_CROP
                setPadding(8, 8, 8, 8)
            }
            imageView.setImageResource(images[position])
            return imageView
        }
    }
}