package com.wefyns.customdialog

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout


//view - dialog want to show
//layout - what layout must show dialog

/*example
*
* RelativeLayout lay = (RelativeLayout) context.findViewById(R.id.Workout_Main);                                //find layout
* View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.workout_muscle_dialog, lay, false);      //inflate dialog
* CustomDialog dialog = new CustomDialog(context, dialogView, lay);                                 //create dialog
* dialog.show();                                                                                                //show dialog
*
*
* functions :
*
* setMargins(dp, dp) - set margins on the sides
* setMargins(dp, dp, dp, dp) - left top right bottom
* align | Top | Center | Bottom - align your dialog in parent (default Center)
* setBackgroundBlackout(float) - set blackout before dialog
* setCanceble() - if you click out of dialog view, dialog will be closed
* setParentEnabled(bollean, layout) - if you don't use blackout, you can disable background lay
*
* setCancebleBackButton() - closed dialog by android back button. need impl callbacks intefaces.
* DOCUMENTATION OF THIS METHOD: https://docs.google.com/document/d/1hREJEilSP6vGttuI4sse6m8rGFQfqPOlYjlvIS_XkHM/edit?usp=sharing
*
* show() - open dialog
* exit() - close dialog
*
* */

class CustomDialog(val context: Context, val view: View, val layout: RelativeLayout) {

    interface IBackButton {
        fun enable()
        fun disable()
    }

    interface IExit {
        fun exit()
    }

    var iBackButton: IBackButton? = null
    var iExit: IExit? = null

    private var IS_BLACKOUT: Boolean = false
    private var IS_CANCEBLE: Boolean = false
    private var dialogGroup: RelativeLayout = RelativeLayout(context)
    private val blackout = View(context)
    private var outView = View(context)

    init {
        dialogGroup.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        dialogGroup.addView(blackout)
        dialogGroup.addView(outView)
        dialogGroup.addView(view.apply {
            setOnClickListener { }
            layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
        })
        dialogGroup.visibility = View.INVISIBLE

        alignCenter()
        layout.addView(dialogGroup)
    }

    fun alignTop() {
        val layoutParams = view.getLayoutParams() as RelativeLayout.LayoutParams
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE)
        view.layoutParams = layoutParams
    }

    fun alignCenter() {
        val layoutParams = view.getLayoutParams() as RelativeLayout.LayoutParams
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        view.layoutParams = layoutParams
    }

    fun alignBottom() {
        val layoutParams = view.getLayoutParams() as RelativeLayout.LayoutParams
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
        view.layoutParams = layoutParams
    }

    fun setMargins(left: Int, right: Int) {
        val scale: Float = context.getResources().getDisplayMetrics().density
        val lp = view.getLayoutParams() as RelativeLayout.LayoutParams
        lp.setMargins(left * scale.toInt(), 0, right * scale.toInt(), 0)
        view.layoutParams = (lp)
    }

    fun setMargins(left: Int, top: Int, right: Int, bottom: Int) {
        val scale: Float = context.getResources().getDisplayMetrics().density
        val lp = view.getLayoutParams() as RelativeLayout.LayoutParams
        lp.setMargins(left * scale.toInt(), top * scale.toInt(), right * scale.toInt(), bottom * scale.toInt())
        view.layoutParams = (lp)
    }

    fun setBackgroundBlackout(alpha: Float) {
        if (!IS_BLACKOUT) {
            blackout.apply {
                setBackgroundColor(Color.BLACK)
                this.alpha = alpha
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )
            }
            IS_BLACKOUT = true
        } else {
            blackout.alpha = alpha
        }
    }

    fun setCanceble() {
        if (!IS_CANCEBLE) {
            outView.apply {
                layoutParams = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )
                setOnClickListener {
                    exit()
                }
            }
            IS_CANCEBLE = true
        }
    }

    fun setCancebleBackButton() {
        iBackButton?.disable()
    }


    fun setParentEnabled(status: Boolean, group: ViewGroup) {
        for (child in 0..group.childCount - 2) {
            group.getChildAt(child).apply {
                isEnabled = status
                if (status) {
                    alpha = 1f
                } else alpha = 0.5f
            }
            if (group.getChildAt(child) is ViewGroup) {
                setParentEnabled(status, group.getChildAt(child) as ViewGroup)
            }
        }
    }

    fun show() {
        dialogGroup.visibility = View.VISIBLE
    }

    fun setOnExitListener(iExit: IExit) {
        this.iExit = iExit
    }

    fun exit() {
        iExit?.exit()
        iBackButton?.enable()
        dialogGroup.visibility = View.INVISIBLE
    }
}