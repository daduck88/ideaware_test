package com.ideaware.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ideaware.testapp.ui.matches.MatchesFragment
import com.ideaware.testapp.ui.matches.TabAdapter


class MainActivity : AppCompatActivity() {
    private val adapter: TabAdapter by lazy { TabAdapter(supportFragmentManager) }
    private val tabLayout: TabLayout by lazy { findViewById<TabLayout>(R.id.tabLayout) }
    private val viewPager: ViewPager by lazy { findViewById<ViewPager>(R.id.viewPager) }
    private val toolbar: Toolbar by lazy { findViewById<Toolbar>(R.id.toolbar) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.matches_title)
        initTabs()

    }

    private fun initTabs() {
        adapter.addFragment(MatchesFragment.newInstance(MatchesFragment.FIXTURES), getString(R.string.fixtures_title))
        adapter.addFragment(MatchesFragment.newInstance(MatchesFragment.RESULTS), getString(R.string.results_title))

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
