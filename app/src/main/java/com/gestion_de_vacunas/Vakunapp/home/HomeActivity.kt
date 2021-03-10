package com.gestion_de_vacunas.Vakunapp.home

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ui.AppBarConfiguration
import com.gestion_de_vacunas.Vakunapp.Activity_Acercade
import com.gestion_de_vacunas.Vakunapp.AppPreferences
import com.gestion_de_vacunas.Vakunapp.MainActivity
import com.google.android.material.navigation.NavigationView
import com.gestion_de_vacunas.Vakunapp.R
import com.gestion_de_vacunas.Vakunapp.home.infovacunas.InfoVacunaListFragment
import com.gestion_de_vacunas.Vakunapp.home.carnet.CarnetListFragment
import com.gestion_de_vacunas.Vakunapp.home.miembro.MiembrosListFragment
import com.gestion_de_vacunas.Vakunapp.home.perfil.EditarFormActivity
import com.gestion_de_vacunas.Vakunapp.home.plan.PlanListFragment
import com.gestion_de_vacunas.Vakunapp.home.recordatorio.RecordatorioListFragment
import com.gestion_de_vacunas.Vakunapp.home.maps.Activity_Maps
import com.gestion_de_vacunas.Vakunapp.home.noticias.NoticiasListFragment
import com.gestion_de_vacunas.Vakunapp.home.viajar.ViajarListFragment


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private val drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_home)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        if (toolbar != null) setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val menuItem = navigationView.menu.getItem(0)
        onNavigationItemSelected(menuItem)
        menuItem.isChecked = true


        val headerView: View = navigationView.getHeaderView(0)
        val accountName = headerView.findViewById<TextView>(R.id.accountName)
        accountName.text = AppPreferences.username.toString()

        accountName.setOnClickListener {
            Log.d("CLICK MENU", "CLICK MENU")
            val intent = Intent(this, EditarFormActivity::class.java)
            startActivity(intent)
        }


        drawer.addDrawerListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var title: Int = 1
        var fragment: Fragment = RecordatorioListFragment()

        when (item.itemId) {
            R.id.nav_family -> {
                title = R.string.nucleo_familiar;
                fragment = MiembrosListFragment()
            }
            R.id.nav_pvacunacion -> {
                title = R.string.plan_vacunacion;
                fragment = PlanListFragment()
            }
            R.id.nav_cvacunacion -> {
                title = R.string.carnet_vacunacion;
                fragment = CarnetListFragment()
            }
            R.id.nav_recordatorios -> {
                title = R.string.recordatorios;
                fragment = RecordatorioListFragment()
            }
            R.id.nav_Noticias -> {
                title = R.string.Noticias;
                fragment = NoticiasListFragment()
            }
            R.id.nav_Maps -> {
                title = R.string.Maps;
                fragment = Activity_Maps()
            }
            R.id.nav_Viajar -> {
                title = R.string.Viajar;
                fragment = ViajarListFragment()
            }
            R.id.nav_InfoVacunas -> {
                title = R.string.InfoVacuna;
                fragment = InfoVacunaListFragment()
            }

            R.id.nav_AcercaDe -> {
                title = R.string.acerca_de
                val intent = Intent(this, Activity_Acercade::class.java)
                startActivity(intent);

            }

            R.id.nav_logout -> {
                title = R.string.Logout;
                AppPreferences.isLogin = false
                AppPreferences.uid = ""
                AppPreferences.username = ""

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent);
                return true
            }
        }

        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
                .replace(R.id.home_content, fragment, getString(title) )
                .commit()

        setTitle(getString(title))
        drawer.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

    override fun onDrawerSlide(view: View, v: Float) {
    }

    override fun onDrawerOpened(view: View) {
    }

    override fun onDrawerClosed(view: View) {
    }

    override fun onDrawerStateChanged(i: Int) {
    }
}