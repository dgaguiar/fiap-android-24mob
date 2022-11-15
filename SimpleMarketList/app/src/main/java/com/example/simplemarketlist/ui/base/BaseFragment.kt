import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.simplemarketlist.MainActivity
import com.example.simplemarketlist.R

abstract class BaseFragment : Fragment() {
    abstract val layout: Int
    private lateinit var loadingView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val screenRootView = FrameLayout(requireContext())
        val screenView = inflater.inflate(layout, container, false)
        loadingView = inflater.inflate(R.layout.include_loading, container, false)
        screenRootView.addView(screenView)
        screenRootView.addView (loadingView)
        return screenRootView
    }

    fun showLoading(message: String = "Processando a requisição") {
        loadingView.visibility = View.VISIBLE
        if (message.isNotEmpty()) loadingView.findViewById<TextView>(R.id.tvLoading).text = message
    }

    fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    fun showToolBar(){
        (activity as MainActivity?)!!.setDrawer_unlocked()
    }

    fun hideToolBar(){
        (activity as MainActivity?)!!.setDrawer_locked()
    }

    fun isFreeVersion(): Boolean {
        return requireActivity().getPackageName() == "com.example.simplemarketlist.free"
    }
}