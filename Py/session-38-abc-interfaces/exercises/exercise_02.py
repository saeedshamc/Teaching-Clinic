# ============================================================
# پاسخ نمونه — تمرین جلسه ۳۸ (پیشرفته)
# ============================================================

from abc import ABC, abstractmethod


class DataExporter(ABC):
    def export(self, rows: list[dict]) -> None:
        content = self.format(rows)
        self.write(content)
        print(f"  exported {len(rows)} rows via {type(self).__name__}")

    @abstractmethod
    def format(self, rows: list[dict]) -> str:
        pass

    @abstractmethod
    def write(self, content: str) -> None:
        pass


class CsvExporter(DataExporter):
    def format(self, rows: list[dict]) -> str:
        if not rows:
            return ""
        keys = rows[0].keys()
        lines = [",".join(keys)]
        for r in rows:
            lines.append(",".join(str(r[k]) for k in keys))
        return "\n".join(lines)

    def write(self, content: str) -> None:
        print("CSV output:\n", content)


class JsonExporter(DataExporter):
    def format(self, rows: list[dict]) -> str:
        import json
        return json.dumps(rows, ensure_ascii=False, indent=2)

    def write(self, content: str) -> None:
        print("JSON output:\n", content)


data = [{"name": "سعید", "score": 90}, {"name": "مریم", "score": 95}]
for exporter in (CsvExporter(), JsonExporter()):
    exporter.export(data)
