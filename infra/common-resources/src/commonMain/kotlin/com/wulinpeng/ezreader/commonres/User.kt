package com.wulinpeng.ezreader.commonres

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.wulinpeng.ezreader.CommonRes

public val CommonRes.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user = Builder(name = "User", defaultWidth = 200.0.dp, defaultHeight = 200.0.dp,
                viewportWidth = 1024.0f, viewportHeight = 1024.0f).apply {
            path(fill = SolidColor(Color(0xFF592B0C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(445.78f, 378.31f)
                curveToRelative(-19.46f, 0.0f, -35.27f, 15.82f, -35.27f, 35.27f)
                verticalLineTo(450.56f)
                curveToRelative(0.0f, 19.46f, 15.82f, 35.27f, 35.27f, 35.27f)
                reflectiveCurveToRelative(35.27f, -15.82f, 35.27f, -35.27f)
                verticalLineToRelative(-36.98f)
                curveToRelative(0.0f, -19.46f, -15.82f, -35.27f, -35.27f, -35.27f)
                close()
                moveTo(615.88f, 485.83f)
                curveToRelative(19.46f, 0.0f, 35.27f, -15.82f, 35.27f, -35.27f)
                verticalLineToRelative(-36.98f)
                curveToRelative(0.0f, -19.46f, -15.82f, -35.27f, -35.27f, -35.27f)
                reflectiveCurveToRelative(-35.27f, 15.82f, -35.27f, 35.27f)
                verticalLineTo(450.56f)
                curveToRelative(0.0f, 19.46f, 15.82f, 35.27f, 35.27f, 35.27f)
                close()
            }
            path(fill = SolidColor(Color(0xFF592B0C)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(791.67f, 841.27f)
                curveToRelative(-0.11f, -1.02f, -0.23f, -2.05f, -0.46f, -2.96f)
                curveToRelative(-6.14f, -33.68f, -22.98f, -65.54f, -47.45f, -89.54f)
                curveToRelative(-9.33f, -9.22f, -19.46f, -16.95f, -30.38f, -23.21f)
                curveToRelative(16.61f, -11.38f, 32.2f, -24.35f, 46.76f, -38.8f)
                curveToRelative(61.33f, -61.33f, 95.12f, -142.9f, 95.12f, -229.72f)
                reflectiveCurveToRelative(-33.79f, -168.39f, -95.12f, -229.72f)
                reflectiveCurveToRelative(-142.9f, -95.12f, -229.72f, -95.12f)
                curveToRelative(-86.81f, 0.0f, -168.39f, 33.79f, -229.72f, 95.12f)
                curveToRelative(-61.33f, 61.33f, -95.12f, 142.9f, -95.12f, 229.72f)
                reflectiveCurveToRelative(33.79f, 168.39f, 95.12f, 229.72f)
                curveToRelative(14.45f, 14.45f, 30.15f, 27.42f, 46.76f, 38.8f)
                curveToRelative(-10.58f, 6.26f, -20.59f, 13.99f, -29.58f, 23.1f)
                curveToRelative(-23.67f, 24.01f, -40.05f, 56.78f, -46.08f, 92.16f)
                curveToRelative(-0.11f, 0.57f, -0.23f, 1.25f, -0.23f, 1.82f)
                lineTo(265.1f, 898.84f)
                curveToRelative(-2.28f, 19.34f, 11.61f, 36.86f, 30.95f, 39.14f)
                curveToRelative(1.37f, 0.11f, 2.73f, 0.23f, 4.1f, 0.23f)
                curveToRelative(17.64f, 0.0f, 32.88f, -13.2f, 35.04f, -31.18f)
                lineToRelative(6.37f, -55.3f)
                curveToRelative(8.99f, -51.09f, 44.94f, -74.3f, 74.64f, -74.3f)
                horizontalLineToRelative(1.82f)
                lineToRelative(42.89f, -2.28f)
                curveToRelative(0.68f, 0.0f, 1.25f, -0.11f, 1.82f, -0.11f)
                curveToRelative(21.96f, 4.66f, 44.6f, 7.05f, 67.58f, 7.05f)
                curveToRelative(23.1f, 0.0f, 45.74f, -2.39f, 67.81f, -7.05f)
                curveToRelative(0.68f, 0.11f, 1.37f, 0.11f, 2.16f, 0.23f)
                lineToRelative(42.44f, 2.28f)
                horizontalLineToRelative(1.82f)
                curveToRelative(39.25f, 0.0f, 69.63f, 36.52f, 76.8f, 72.36f)
                lineToRelative(5.35f, 56.43f)
                curveToRelative(1.71f, 18.2f, 17.07f, 31.97f, 35.04f, 31.97f)
                curveToRelative(1.14f, 0.0f, 2.28f, 0.0f, 3.41f, -0.11f)
                curveToRelative(19.34f, -1.82f, 33.68f, -19.0f, 31.74f, -38.46f)
                lineToRelative(-5.23f, -58.48f)
                close()
                moveTo(472.18f, 706.9f)
                curveToRelative(-2.73f, -1.14f, -5.69f, -1.82f, -8.76f, -2.16f)
                curveToRelative(-42.78f, -11.49f, -82.03f, -34.13f, -114.23f, -66.33f)
                curveToRelative(-48.47f, -48.47f, -75.21f, -112.87f, -75.21f, -181.48f)
                reflectiveCurveToRelative(26.74f, -133.01f, 75.21f, -181.48f)
                reflectiveCurveTo(462.05f, 200.25f, 530.66f, 200.25f)
                reflectiveCurveToRelative(133.01f, 26.74f, 181.48f, 75.21f)
                curveToRelative(48.47f, 48.47f, 75.21f, 112.87f, 75.21f, 181.48f)
                reflectiveCurveTo(760.49f, 589.94f, 712.02f, 638.29f)
                curveToRelative(-32.31f, 32.31f, -71.68f, 54.95f, -114.69f, 66.45f)
                curveToRelative(-2.39f, 0.34f, -4.78f, 0.91f, -7.05f, 1.82f)
                curveToRelative(-19.34f, 4.55f, -39.37f, 6.94f, -59.73f, 6.94f)
                curveToRelative(-19.91f, 0.0f, -39.48f, -2.28f, -58.37f, -6.6f)
                close()
            }
        }
        .build()
        return _user!!
    }

private var _user: ImageVector? = null
