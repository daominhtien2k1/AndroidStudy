package com.example.recyclerviewscrollvaryingheight


import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//  The LayoutManager is responsible for positioning items as well as recycling items in a RecyclerView
class CoolLayoutManager(context: Context?) : LinearLayoutManager(context) {
    init {
        isSmoothScrollbarEnabled = false
    }

    /*
       ListView with 30 items has scrollRange equals to 3000, that is due to
          scrollRange = numberOfItems * 100
          scrollExtent = numberOfVisibleItems * 100
          scrollOffset = numberOfScrolledItems * 100
    */
    /*
       The units for these methods is arbitrary; you can use anything you'd like as long as all three methods
       share the same units. By default, LinearLayoutManager will use one of two sets of units:
       mSmoothScrollbarEnabled == true: Use units based on the pixel sizes of the visible items in the RecyclerView.
       mSmoothScrollbarEnabled == false: Use units based on the positions of the visible items in the RecyclerView's adapter.
    */



    // To compute the vertical size of the scrollbar indicator (thumb)
    /*
       Compute the vertical extent of the vertical scrollbar's thumb within the vertical range.
       This value is used to compute the length of the thumb within the scrollbar's track.
    */
    /*
       Defines the size of the current window area displayed on the screen, So for example, if your list has 10 items and
       you can currently see 5 of those items, then the extent is 5 (or 5*itemHeight)
     */
    override fun computeVerticalScrollExtent(state: RecyclerView.State): Int {
        // childCount: số item xuất hiện trên màn hình, không cần hiển thị hoàn toàn, mà chỉ cần chớm xuất hiện 1 phần cũng tính
        // Return the current number of child views attached to the parent RecyclerView.

        return if (childCount > 0) {
            SMOOTH_VALUE * 5
            // nếu set là SMOOTH_VALUE * childCount thì thumb kích thước sẽ thay đổi liên tục, do childCount dynamic thay đổi
        } else 0
    }

    //Computes the vertical size of all the content (scrollbar track)
    override fun computeVerticalScrollRange(state: RecyclerView.State): Int {
        return Math.max((itemCount-1) * SMOOTH_VALUE, 0)
    }

    // Computes the vertical distance from the top of the screen (scrollbar position)
    /*
       Defines the distance between the start of the scrollable area and the top of the current view window inside
       the scrollable area. So for example, if your list has 10 items and you've scrolled down so the 3rd item is
       at the top-most visible item, then the offset is 3 (or 3*itemHeight).
    */
    override fun computeVerticalScrollOffset(state: RecyclerView.State): Int {
        // thử return 400 ngay đầu thì thấy thumb luôn nằm ở 1 chỗ cố định
        // không hiểu offset tính ra value kiểu gì mà return 100*1 nhưng lại không nằm ở vị trí item thứ 2
        val count = childCount
        if (count <= 0) {
            return 0
        }
        // itemCount là 50, trong khi index từ 0-49
        // ngay khi item cuối được complete visible thì thumb lập tức nhảy xuống cuối luôn
        if (findLastCompletelyVisibleItemPosition() == itemCount - 1) {
            return Math.max((itemCount - 1) * SMOOTH_VALUE, 0)
        }

        val firstPos = findFirstVisibleItemPosition()
        if (firstPos == RecyclerView.NO_POSITION) {
            return 0
        }
        val firstVisibleView = findViewByPosition(firstPos)
        if (firstVisibleView == null) return 0

        // tính toán % partial view vừa scroll qua
        // nếu view có height là 400 thì khi scroll thì top chạy từ 0 -> -400, qua view đằng sau thì lại reset từ đâu
        val top = getDecoratedTop(firstVisibleView)
        val height = getDecoratedMeasuredHeight(firstVisibleView)
        // heightOfScrolledItem có thể bằng 0 khi là item quá to mà mới cuộn được 1 ít, tức là 100*3/400 (cuộn được 3%)
        val scrolledHeightOfPartialFirstVisibleView: Int = Math.abs(SMOOTH_VALUE * top / height)
        if (scrolledHeightOfPartialFirstVisibleView == 0 && firstPos > 0) {
            return SMOOTH_VALUE * firstPos - 1
        }
        return SMOOTH_VALUE * firstPos + scrolledHeightOfPartialFirstVisibleView
    }

    companion object {
        /*
           SMOOTH_VALUE represents a single item in our list. Therefore, we are using a thumb that is about
           three times the size of a single item.
        */
        private const val SMOOTH_VALUE = 100
    }
}