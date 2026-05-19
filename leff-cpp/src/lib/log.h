#ifndef libfaafo_DGB_H
#define libfaafo_DGB_H

#include <stdio.h>
#include <errno.h>
#include <string.h>

#define __FILENAME__ (strrchr(__FILE__, '/') ? strrchr(__FILE__, '/') + 1 : __FILE__)

#ifdef NDEBUG
#define log_debug(M, ...)
#else

#define log_debug(M, ...) fprintf(stdout, "DEBUG %s:%d %s: " M "\n",\
__FILENAME__, __LINE__, __func__, ##__VA_ARGS__)

#endif

#define clean_errno() (errno == 0 ? "None" : strerror(errno))

#define log_err(M, ...) fprintf(stderr,\
"[ERROR] (%s:%d %s: errno: %s) " M "\n", __FILENAME__, __LINE__,\
__func__, clean_errno(), ##__VA_ARGS__)

#define log_warn(M, ...) fprintf(stderr,\
"[WARN] (%s:%d %s: errno: %s) " M "\n",\
__FILENAME__, __LINE__, __func__, clean_errno(), ##__VA_ARGS__)

#define log_info(M, ...) fprintf(stdout, "[INFO] (%s:%d %s) " M "\n",\
__FILENAME__, __LINE__, __func__, ##__VA_ARGS__)

#endif