package com.konkuk.hackathon.feature.center.register.screen

import android.content.ClipData
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import kotlinx.coroutines.launch

@Composable
fun SuccessRegisterScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    inviteCode: String = "코드명",
    onCheckClick: () -> Unit = {},
    centerName : String = "센터 이름"
) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(OnItTheme.colors.white)
        .padding(padding)) {
        val clipboard = LocalClipboard.current
        val context = LocalContext.current
        val scope = rememberCoroutineScope()

        val onCopyClick: () -> Unit = {
            scope.launch {
                val clip = ClipData.newPlainText("invite_code", inviteCode)
                clipboard.setClipEntry(ClipEntry(clip))
                Toast.makeText(context, "코드가 복사되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(OnItTheme.colors.white)
                .padding(vertical = 12.dp)
                .padding(start = 16.dp)
        ) {
            Text(
                text = "어르신 등록 완료",
                color = OnItTheme.colors.gray7,
                modifier = Modifier
                    .align(Alignment.Center),
                style = OnItTheme.typography.SB_20
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(OnItTheme.colors.white)
                    .border(1.dp, OnItTheme.colors.gray2, RoundedCornerShape(8.dp))
                    .padding(vertical = 43.dp),
                contentAlignment = Alignment.Center
            )
            {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "어르신 등록이 완료되었습니다.",
                        style = OnItTheme.typography.SB_18,
                        color = OnItTheme.colors.gray7
                    )
                    Text(
                        text = "발급된 참여코드를 봉사자에게 전달해 주세요.",
                        style = OnItTheme.typography.B_12,
                        color = OnItTheme.colors.gray3
                    )
                    Text(
                        text = inviteCode,
                        style = OnItTheme.typography.B_26,
                        color = OnItTheme.colors.primary
                    ) // 추후 값 받아오는 방식으로 수정 필요
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(OnItTheme.colors.gray1)
                                .border(
                                    1.dp,
                                    OnItTheme.colors.gray1, RoundedCornerShape(6.dp)
                                )
                                .clickable { onCopyClick() }
                                .padding(horizontal = 36.5.dp, vertical = 6.5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "코드 복사",
                                style = OnItTheme.typography.B_17,
                                color = OnItTheme.colors.gray7
                            )
                        }
                        Box(
                            modifier = modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(OnItTheme.colors.gray1)
                                .border(
                                    1.dp,
                                    OnItTheme.colors.gray1, RoundedCornerShape(6.dp)
                                )
                                .clickable {
                                    val shareText = "[${centerName}]\n참여코드 전달해 드립니다.\n참여코드: $inviteCode"
                                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, shareText)
                                    }
                                    val chooser = Intent.createChooser(sendIntent, "참여코드 공유")
                                    context.startActivity(chooser)
                                }
                                .padding(horizontal = 36.5.dp, vertical = 6.5.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "공유하기",
                                style = OnItTheme.typography.B_17,
                                color = OnItTheme.colors.gray7
                            )
                        }
                    }
                }
            }
            Spacer(modifier = modifier.height(24.dp))
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(14.dp))
                    .fillMaxWidth()
                    .background(color = OnItTheme.colors.primary, shape = RoundedCornerShape(14.dp))
                    .clickable(onClick = { onCheckClick() })
            ) {
                Text(
                    text = "확인",
                    style = OnItTheme.typography.B_17,
                    modifier = Modifier
                        .padding(vertical = 13.dp)
                        .align(Alignment.Center),
                    color = OnItTheme.colors.white
                )
            }
        }

    }
}

@Preview
@Composable
private fun SuccessRegisterPreview() {
    SuccessRegisterScreen(
        modifier = Modifier,
        onCheckClick = {},
        padding = PaddingValues(),
        inviteCode = "123456",
        centerName = "광진노인복지관"
    )
}