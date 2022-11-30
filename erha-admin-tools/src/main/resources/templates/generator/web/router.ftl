import { DEFAULT_LAYOUT } from '@/router/constans';

export default {
  path: '/${apiPathF}',
  name: '${apiPathF?cap_first}',
  component: DEFAULT_LAYOUT,
  meta: {
    requiresAuth: true,
  },
  children: [
    {
      path: '${apiPathS}',
      name: '${apiPathS?cap_first}',
      component: () => import('@/views/${path}/index.vue'),
      meta: {
        locale: 'menu.${apiShort}',
      },
    },
  ],
};