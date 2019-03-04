package com.ideaware.testapp.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ideaware.testapp.R
import com.ideaware.testapp.data.Match
import com.ideaware.testapp.repo.Repository
import androidx.recyclerview.widget.DividerItemDecoration

class MatchesFragment : Fragment() {
    companion object {
        private const val TYPE = "TYPE"
        const val FIXTURES = "FIXTURES"
        const val RESULTS = "RESULTS"
        fun newInstance(type: String): MatchesFragment {
            val myFragment = MatchesFragment()
            val args = Bundle()
            args.putString(TYPE, type)
            myFragment.arguments = args
            return myFragment
        }
    }

    val mType: String by lazy { arguments?.getString(TYPE) ?: FIXTURES }

    private lateinit var adapter: MatchesAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val recyclerView: RecyclerView by lazy { view!!.findViewById<RecyclerView>(R.id.rv_matches) }
    private val progressbar: ProgressBar by lazy { view!!.findViewById<ProgressBar>(R.id.progressbar) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        layoutManager = LinearLayoutManager(context)
        adapter = MatchesAdapter(ArrayList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecor)

        progressbar.visibility = View.VISIBLE

        val repo = Repository()

        if (mType == FIXTURES) {
            repo.getFixtures()
        } else {
            repo.getResults()
        }.subscribe(this::onData, this::onError, { progressbar.visibility = View.GONE })

    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(context, "error: $throwable", Toast.LENGTH_LONG).show()
        throwable.printStackTrace()
    }

    private fun onData(response: List<Match>) {
        adapter.setData(response)
    }
}