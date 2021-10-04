package app.shosetsu.android.view.compose.setting

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import app.shosetsu.android.common.ext.launchIO
import app.shosetsu.common.consts.settings.SettingKey
import app.shosetsu.common.domain.repositories.base.ISettingsRepository
import com.github.doomsdayrs.apps.shosetsu.R

@Composable
fun DropdownSettingContent(
	title: String,
	description: String,
	choices: Array<String>,
	modifier: Modifier = Modifier,
	repo: ISettingsRepository,
	key: SettingKey<Int>
) {
	val choice by repo.getIntFlow(key).collectAsState(key.default)

	DropdownSettingContent(title, description, choice, choices, modifier) {
		launchIO { repo.setInt(key, it) }
	}

}

@Composable
fun DropdownSettingContent(
	title: String,
	description: String,
	selection: Int,
	choices: Array<String>,
	modifier: Modifier = Modifier,
	onSelection: (newValue: Int) -> Unit
) {
	var expanded by remember { mutableStateOf(false) }

	GenericRightSettingLayout(title, description, modifier) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
		) {
			ClickableText(
				text = AnnotatedString(choices[selection]),
			) {
				expanded = true
			}
			IconToggleButton(
				onCheckedChange = {
					expanded = it
				},
				checked = expanded,
				modifier = Modifier.wrapContentWidth()
			) {
				if (expanded)
					Icon(painterResource(R.drawable.expand_less), "")
				else
					Icon(painterResource(R.drawable.expand_more), "")
			}
			DropdownMenu(
				expanded = expanded,
				onDismissRequest = { expanded = false },
			) {
				choices.forEachIndexed { index, s ->
					ClickableText(
						text = AnnotatedString(s),
						modifier = Modifier.padding(8.dp),
						onClick = {
							onSelection(index)
							expanded = false
						})
				}
			}
		}
	}
}