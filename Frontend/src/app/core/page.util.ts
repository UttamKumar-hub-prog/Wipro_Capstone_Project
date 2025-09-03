export function unwrapList<T>(data: T[] | { content: T[] }): T[] {
  return Array.isArray(data) ? data : (data as any).content ?? [];
}
