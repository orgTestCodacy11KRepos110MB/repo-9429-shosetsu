package app.shosetsu.android.datasource.remote.base

import app.shosetsu.lib.IExtension
import app.shosetsu.lib.Novel
import org.luaj.vm2.LuaError
import javax.net.ssl.SSLException

/*
 * This file is part of shosetsu.
 *
 * shosetsu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * shosetsu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with shosetsu.  If not, see <https://www.gnu.org/licenses/>.
 */

/**
 * shosetsu
 * 04 / 05 / 2020
 */
interface IRemoteCatalogueDataSource {

	/**
	 * Runs a search on an extension
	 */
	@Throws(LuaError::class)
	suspend fun search(
		ext: IExtension,
		query: String,
		data: Map<Int, Any>,
	): List<Novel.Listing>


	/**
	 * Loads a listings data from an extension
	 */
	@Throws(SSLException::class, LuaError::class)
	suspend fun loadListing(
		ext: IExtension,
		listingIndex: Int,
		data: Map<Int, Any>,
	): List<Novel.Listing>
}