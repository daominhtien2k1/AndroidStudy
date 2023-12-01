package com.example.recyclerviewsnaphelperdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        val data = List(20) { "Item $it" }
        val adapter = MyAdapter(data)
        recyclerView.adapter = adapter

        //  tự động đưa các item gần trung tâm nhất của RecyclerView vào giữa
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(SnapOnScrollListener(
            snapHelper,
            behavior = SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
            snapPositionChangeListener = object : SnapPositionChangeListener{
            override fun onSnapPositionChange(position: Int) {
                println(position)
            }
        }))

//        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
//            // Sự kiện này được gọi khi RecyclerView được cuộn
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                if (dy > 0) {
////                    println("RecyclerView scrolled down")
//                } else if (dy < 0) {
////                    println("RecyclerView scrolled up")
//                }
//                super.onScrolled(recyclerView, dx, dy)
//            }
//
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                // Sự kiện này được gọi khi trạng thái cuộn thay đổi (chẳng hạn như cuộn dừng lại)
//                when (newState) {
//                    RecyclerView.SCROLL_STATE_IDLE -> {
//                        // Trạng thái dừng cuộn
//                        println("RecyclerView scroll state: Idle")
//                    }
//                    RecyclerView.SCROLL_STATE_DRAGGING -> {
//                        // Trạng thái đang kéo
//                        println("RecyclerView scroll state: Dragging")
//                    }
//                    RecyclerView.SCROLL_STATE_SETTLING -> {
//                        /*
//                        Trạng thái này diễn ra khi RecyclerView đang tự động cuộn đến vị trí nào đó, thường do
//                        một hành động cuộn trước đó đã dừng.
//                        Ví dụ, nếu bạn cuộn một RecyclerView và rồi nhả tay, nó có thể cuộn đến vị trí cuối cùng của một item gần nhất
//                         */
//                        // Trạng thái đang giữ vị trí sau khi cuộn
//                        println("RecyclerView scroll state: Settling")
//                    }
//                }
//                super.onScrollStateChanged(recyclerView, newState)
//            }
//        })


        // Set both RecyclerView and the items of the RecyclerView.Adapter to have MATCH_PARENT height and width
        // làm cho 1 item hiển thị trên toàn màn hình, và hoạt động tương tự như một Viewpager.
        // nếu không set match_parent thì thấy giống LinearSnapHelper
//        val snapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(recyclerView)
    }


}


interface SnapPositionChangeListener {
    fun onSnapPositionChange(position: Int)
}

class SnapOnScrollListener(
    private val snapHelper: SnapHelper,
    var behavior: Behavior = Behavior.NOTIFY_ON_SCROLL,
    var snapPositionChangeListener: SnapPositionChangeListener? = null
) : RecyclerView.OnScrollListener() {

    enum class Behavior {
        NOTIFY_ON_SCROLL,
        NOTIFY_ON_SCROLL_STATE_IDLE
    }

    private var snapPosition = RecyclerView.NO_POSITION

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        // luôn luôn notify khi đang scroll
        if (behavior == Behavior.NOTIFY_ON_SCROLL) {
            maybeNotifySnapPositionChange(recyclerView)
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        // chỉ notify khi kết thúc scroll
        if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_IDLE) {
            maybeNotifySnapPositionChange(recyclerView)
        }
    }

    private fun maybeNotifySnapPositionChange(recyclerView: RecyclerView) {
        val snapPosition = snapHelper.getSnapPosition(recyclerView)
        val snapPositionChanged = this.snapPosition != snapPosition
        if (snapPositionChanged) {
            snapPositionChangeListener?.onSnapPositionChange(snapPosition)
            this.snapPosition = snapPosition
        }
    }

    // Tìm vị trí snap (snap position)
    private fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }
}
